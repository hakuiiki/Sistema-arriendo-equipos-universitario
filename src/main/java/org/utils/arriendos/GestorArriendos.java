package org.utils.arriendos;

import org.utils.clientes.Clientes;
import org.utils.equipos.Equipo;
import org.utils.excepciones.*;
import org.utils.repositorio.Repositorio;

import java.time.LocalDate;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;


public class GestorArriendos {

    Repositorio<Clientes> repositorioClientes;
    Repositorio<Equipo> repositorioEquipos;
    Repositorio<Arriendo> repositorioArriendos;

    private int siguienteIdArriendo;

    /** para el constructor, los repositorios los tomaremos desde el main para no empezar
     * creando uno desde 0 cada que se inicie el programa
     */
    public GestorArriendos(Repositorio<Clientes> repositorioClientes,
                           Repositorio<Equipo> repositorioEquipos,
                           Repositorio<Arriendo> repositorioArriendos){

        this.repositorioClientes = repositorioClientes;
        this.repositorioEquipos = repositorioEquipos;
        this.repositorioArriendos = repositorioArriendos;
        this.siguienteIdArriendo = 1;
    }

    /** metodos auxiliares de busqueda */

    public Arriendo buscarArriendoPorId(int idArriendo) throws ArriendoNoEncontradoException {
        Arriendo arriendoBuscado = null;
        for(Arriendo arriendo : repositorioArriendos.obtenerRepositorio()){
            if(arriendo.getId() == idArriendo){
                arriendoBuscado = arriendo;
                return arriendoBuscado;
            }
        }

        throw new ArriendoNoEncontradoException("El arriendo ingresado por su ID: " + idArriendo + " no fue encontrado.");

    }

    private Equipo buscarEquipoPorId(int idEquipo) throws EquipoNoEncontradoException {

        for (Equipo equipo : repositorioEquipos.obtenerRepositorio()) {
            if (equipo.getId() == idEquipo) {
                return equipo;
            }
        }

        throw new EquipoNoEncontradoException("No se encontró un equipo con el ID: " + idEquipo);
    }

    private Clientes buscarClientePorId(int idCliente) throws ClienteNoEncontradoException {

        for (Clientes cliente : repositorioClientes.obtenerRepositorio()) {
            if (cliente.getId() == idCliente) {
                return cliente;
            }
        }

        throw new ClienteNoEncontradoException("No se encontró un cliente con el ID: " + idCliente);
    }

    public Arriendo iniciarArriendo(int idClienteBuscado, int idEquipoBuscado, LocalDate fechaInicio, LocalDate fechaDevolucionEsperada)
            throws ClienteNoEncontradoException, EquipoNoEncontradoException, ClienteNoPuedeArrendarException,
            LimiteArriendosExcedidoException, AccionArriendoInvalidaException{

        // 1 - Buscaremos al cliente del arriendo a iniciar segun su ID en el Repositorio Clientes
        // usamos obtenerRepositorio para obtener el objeto ArrayList el cual es iterable con el for-each
        // porque repositorioClientes como objeto Repositorio no es iterable
        Clientes clienteArriendo = buscarClientePorId(idClienteBuscado);

        // 2 - Buscaremos al equipo del arriendo segun su ID en el Repositorio
        Equipo equipoArriendo = buscarEquipoPorId(idEquipoBuscado);


        // 3 - Necesitamos verificar que el cliente pueda arrendar
        if (clienteArriendo.isPuedeArrendar() == false){
            throw new ClienteNoPuedeArrendarException("El cliente no puede arrendar");
        }

        // 4 - Verificamos que el limite de arriendos del cliente no haya sido excedido
        // para ello primero hacemos un conteo de los arriendos actuales que posea el cliente en el Repositorio Arriendos
        // y lo comparamos con el limite de arriendos de su clase

        int conteoArriendos = 0;
        for(Arriendo arriendo : repositorioArriendos.obtenerRepositorio()){
            /** comparamos segun sus IDs, ya que son unicos, ademas solo contamos los arriendos que esten activos por eso el && */
            if (clienteArriendo.getId() == arriendo.getCliente().getId() && arriendo.estaActivo()){
                conteoArriendos++;
            }
        }

        if(conteoArriendos >= clienteArriendo.obtenerLimiteArriendos()){
            throw new LimiteArriendosExcedidoException("El cliente ya alcanzo su limite de arriendos previamente");
        }


        // 5 - Calculamos el costo final del arriendo, para ello consideramos:
        // - valor base
        // - descuento segun el tipo de cliente

        // Y aparte calculamos la garantia del cliente, para asi llegar al monto total que debe poseer de saldo el cliente


        int costoArriendo = 0;

        // 5.1 - Calculamos cuantos dias dura el arriendo
        long cantidadDias = ChronoUnit.DAYS.between(fechaInicio, fechaDevolucionEsperada);


        // 5.2 - La devolución debe ser posterior al inicio.
        if (cantidadDias <= 0) {
            throw new AccionArriendoInvalidaException(
                    "La fecha de devolución esperada debe ser posterior a la fecha de inicio"
            );
        }

        // 5.3 - Calculamos el valor base sin descuento: tarifa diaria * cantidad de dias
        int valorBase = equipoArriendo.getTipoEquipo().getTarifaDiaria() * (int)cantidadDias;

        // 5.4 - Calculamos el valor del descuento segun el tipo de cliente: valor base * porcentaje de descuento
        int valorDescuento = (int)Math.round( valorBase * clienteArriendo.getPorcentajeDescuento() );

        // 5.5 - Calculamos el costo final restando: valor base - valor del descuento
        costoArriendo = valorBase - valorDescuento;

        // 5.6 - Calculamos la garantia a cobrar, que es adicional al costo final del producto
        int garantiaCobrada = clienteArriendo.calcularGarantia( equipoArriendo.getTipoEquipo().getGarantiaBase() );

        // 5.7 - Sumamos el costo final + la garantia a cobrar, pues este sera el valor total general que tendra el arriendo
        // y del cual el cliente debe disponer del saldo para ello
        int valorTotalFinal = costoArriendo + garantiaCobrada;


        // 6 - Verificamos que el cliente posea saldo suficiente.

        if (clienteArriendo.getSaldoCliente() < valorTotalFinal){
            throw new SaldoInsuficienteException("El cliente no posee saldo suficiente para iniciar el arriendo");
        }

        // 7 - Una vez pasadas las validaciones, descontamos el saldo del cliente para dar inicio al cobro del arriendo
        clienteArriendo.descontarSaldo(valorTotalFinal);

        // 8 - Marcamos el equipo como arrendado antes de crear su objeto de Arriendo
        equipoArriendo.marcarArrendado();

        // 9 - Creamos el arriendo mediante la creacion del objeto Arriendo con los datos del cliente y equipo

        Arriendo nuevoArriendo = new Arriendo(siguienteIdArriendo, clienteArriendo, equipoArriendo,
                fechaInicio, fechaDevolucionEsperada, costoArriendo, garantiaCobrada);

        // 10 - Agregamos el nuevo arriendo al repositorio de arriendos creado
        repositorioArriendos.agregarElemento(nuevoArriendo);

        // 11 - Avanzamos el contador de IDs para los arriendos
        siguienteIdArriendo++;

        // 12 - Retornamos el arriendo iniciado
        return nuevoArriendo;
    }


    public void registrarDevolucion(int idArriendoDevolucion, LocalDate fechaDevolucionEsperada, LocalDate fechaDevolucionReal,
                                    boolean equipoDañado) throws ArriendoNoEncontradoException, AccionArriendoInvalidaException{

        // 1 -  Busca el arriendo en el repositorio de arriendos segun su ID
        Arriendo arriendoBuscado = buscarArriendoPorId(idArriendoDevolucion);

        // 2 - Comprobamos que el arriendo que buscamos siga activo
        // Pues no debemos permitir que se intente devolver dos veces el mismo arriendo
        if(!arriendoBuscado.estaActivo()){
            throw new AccionArriendoInvalidaException("El arriendo esta inactivo");
        }

        // 3 - Verificamos si hubo atraso en la devolucion del arriendo para hacer una multa
        // la multa sera del 50% del valor de la tarifa diaria
        // si no le alcanza pa pagarla cago nomas ajajja bloqueada por perra
        int tarifaDiaria = arriendoBuscado.getEquipo().getTipoEquipo().getTarifaDiaria();
        int multaDiaria = (int) Math.round(tarifaDiaria * 0.50);

        long diasAtraso = ChronoUnit.DAYS.between(fechaDevolucionEsperada, fechaDevolucionReal);
        boolean clienteBloqueado = false;

        if (arriendoBuscado.estaAtrasado(fechaDevolucionReal)){
            int multa = Math.toIntExact(multaDiaria * diasAtraso);
            int saldoCliente = arriendoBuscado.getCliente().getSaldoCliente();

            if (saldoCliente >= multa){
                arriendoBuscado.getCliente().descontarSaldo(multa);
            } else {
                clienteBloqueado = true;
                arriendoBuscado.getCliente().bloquearArriendos();
            }
        }

        // 4 - Marcamos el equipo como disponible o arrendado, ademas cobramos garantia segun la circunstancia
        if (equipoDañado){
            arriendoBuscado.getEquipo().enviarMantenimiento();
            // la garantia aca NO la devolvemos
        } else {
            arriendoBuscado.getEquipo().marcarDisponible();
            int garantiaDevolver = arriendoBuscado.getGarantiaCobrada();

            arriendoBuscado.getCliente().agregarSaldo(garantiaDevolver);

        }
    }

    /** metodo que devuelve la lista de arriendos de un cliente en particular, segun la id ingresada */
    public ArrayList<Arriendo> obtenerArriendosCliente(int idCliente) throws ClienteNoEncontradoException {

        // verificamos que el cliente exista
        buscarClientePorId(idCliente);

        ArrayList<Arriendo> arriendosCliente = new ArrayList<>();

        for(Arriendo arriendo : repositorioArriendos){
            if(arriendo.getCliente().getId() == idCliente){
                arriendosCliente.add(arriendo);
            }
        }

        return arriendosCliente;
    }

    public ArrayList<Arriendo> obtenerArriendosActivos() {

        ArrayList<Arriendo> arriendosActivos = new ArrayList<>();

        for (Arriendo arriendo : repositorioArriendos.obtenerRepositorio()) {

            if (!arriendo.isArriendoFinalizado()) {
                arriendosActivos.add(arriendo);
            }
        }

        return arriendosActivos;
    }

    public ArrayList<Arriendo> obtenerArriendosAtrasados(LocalDate fechaConsulta)
            throws AccionArriendoInvalidaException {

        ArrayList<Arriendo> arriendosAtrasados = new ArrayList<>();

        for (Arriendo arriendo : repositorioArriendos.obtenerRepositorio()) {

            if (arriendo.estaAtrasado(fechaConsulta)) {
                arriendosAtrasados.add(arriendo);
            }
        }

        return arriendosAtrasados;
    }

    /** metodo para obtener el repositorio de arriendos ordenado segun el criterio de la clase Arriendo.java con su compareTo() */
    public ArrayList<Arriendo> obtenerArriendosOrdenados() {

        ArrayList<Arriendo> arriendosOrdenados = repositorioArriendos.obtenerRepositorio();

        Collections.sort(arriendosOrdenados);

        return arriendosOrdenados;
    }
}
