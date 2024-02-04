package org.iesalandalus.programacion.reservashotel.vista;


import org.iesalandalus.programacion.reservashotel.Opcion;
import org.iesalandalus.programacion.reservashotel.controlador.Controlador;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.Entrada;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Habitaciones;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Huespedes;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Reservas;



import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Vista {


    private Controlador controlador;
    private Habitaciones habitaciones;
    private Huespedes huespedes;
    private Reservas reservas;



    public Vista (){}

    public void setControlador(Controlador controlador)
    {
        if (controlador == null)
        {
            throw new NullPointerException("Controlador no puede ser nulo");
        }
        else {
            this.controlador = controlador;
        }
    }

    public void comenzar() {

        System.out.println(" ");
        System.out.println(" .................................");
        System.out.println(" ");
        System.out.println("Bienvenido al hotel IES Al-Andalus ");
        System.out.println(" ");
        System.out.println(" .................................");
        System.out.println(" ");

        Opcion opcion = null;

        while (opcion != Opcion.SALIR) {
            System.out.println(" ");
            Consola.mostrarMenu();
            System.out.println(" ");
            opcion = Consola.elegirOpcion();
            ejecutarOpcion(opcion);
        }

        controlador.terminar();
    }

    public void terminar() {
        System.out.println(" ");
        System.out.println(" .................................");
        System.out.println(" ");
        System.out.println("Vuelva pronto.");
        System.out.println(" ");
        System.out.println(" .................................");
        System.out.println(" ");
    }

    //Métodos que antes estaban en la clase MainApp

    private void ejecutarOpcion(Opcion opcion) {


        switch (opcion) {
            case SALIR -> {
                System.out.println("Cerrando la aplicación...");
                System.exit(0);
                break;
            }
            case INSERTAR_HUESPED -> insertarHuesped();
            case BUSCAR_HUESPED -> buscarHuesped();
            case BORRAR_HUESPED -> borrarHuesped();
            case MOSTRAR_HUESPEDES -> mostrarHuespedes();
            case INSERTAR_HABITACION -> insertarHabitacion();
            case BUSCAR_HABITACION -> buscarHabitacion();
            case BORRAR_HABITACION -> borrarHabitacion();
            case MOSTRAR_HABITACIONES -> mostrarHabitaciones();
            case INSERTAR_RESERVA -> insertarReserva();
            case ANULAR_RESERVA -> anularReserva();
            case MOSTRAR_RESERVAS -> mostrarReservas();
            case CONSULTAR_DISPONIBILIDAD -> {

                TipoHabitacion tH = Consola.leerTipoHabitacion();
                LocalDate fechaI = Consola.leerFecha("Fecha de inicio de reserva:");
                LocalDate fechaF = Consola.leerFecha("Fecha de fin de reserva:");

                consultarDisponibilidad(tH, fechaI, fechaF);
            }
            case REALIZAR_CHECKIN -> realizarCheckin();
            case REALIZAR_CHECKOUT -> realizarCheckout();

        }
    }

    private void insertarHuesped() {
        try {
            Huesped nuevoHuesped = Consola.leerHuesped();
            if (huespedes.buscar(nuevoHuesped) == null) {
                try {
                    huespedes.insertar(nuevoHuesped);
                    System.out.println("Hu?sped insertado correctamente.");
                } catch (OperationNotSupportedException e) {
                    System.out.println("Error al insertar el hu?sped: " + e.getMessage());
                }
            } else {
                System.out.println("El hu?sped ya est? registrado en el sistema.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error al leer el hu?sped: " + e.getMessage());
        }
    }

    private void buscarHuesped() {
        try {
            Huesped huespedFicticio = Consola.getHuespedPorDni();
            Huesped huespedEncontrado = huespedes.buscar(huespedFicticio);
            if (huespedEncontrado != null) {
                System.out.println("Hu?sped encontrado: " + huespedEncontrado);
            } else {
                System.out.println("No se encontr? ning?n hu?sped con el DNI proporcionado.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error al buscar el hu?sped: " + e.getMessage());
        }
    }

    private void borrarHuesped() {
        try {
            Huesped huespedFicticio = Consola.getHuespedPorDni();
            Huesped huespedBorrado = huespedes.buscar(huespedFicticio);
            if (huespedBorrado != null) {
                huespedes.borrar(huespedBorrado);
                System.out.println("Hu?sped borrado: " + huespedBorrado);
            } else {
                System.out.println("No se encontr? ning?n hu?sped con el DNI proporcionado.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error al buscar el hu?sped: " + e.getMessage());
        }
    }


    private void mostrarHuespedes() {
        if (huespedes.getTamano() == 0) {
            System.out.println("No hay hu?spedes almacenados.");
        } else {
            System.out.println("Lista de hu?spedes almacenados:");
            for (Huesped huesped : huespedes.get()) {
                System.out.println(huesped.toString());
            }
        }
    }

    private void insertarHabitacion() {
        try {
            Habitacion nuevaHabitacion = Consola.leerHabitacion();

            if (habitaciones.buscar(nuevaHabitacion) == null) {
                try {
                    habitaciones.insertar(nuevaHabitacion);
                    System.out.println("Habitacion insertada correctamente.");
                } catch (OperationNotSupportedException e) {
                    System.out.println("Error al insertar la habitacion: " + e.getMessage());
                }
            } else {
                System.out.println("La habitacion ya est? registrada en el sistema.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error al leer la habitaci?n: " + e.getMessage());
        }
    }

    private void buscarHabitacion() {
        try {
            Habitacion habitacionFicticia = Consola.leerHabitacionPorIdentificador();
            Habitacion habitacionEncontrada = habitaciones.buscar(habitacionFicticia);
            if (habitacionEncontrada != null) {
                System.out.println("Habitaci?n encontrada: " + habitacionEncontrada);
            } else {
                System.out.println("La habitaci?n buscada no se encuentra en la colecci?n.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error al buscar la habitaci?n: " + e.getMessage());
        }
    }



    private void borrarHabitacion() {
        try {
            Habitacion habitacionFicticia = Consola.leerHabitacionPorIdentificador();
            Habitacion habitacionBorrada = habitaciones.buscar(habitacionFicticia);
            if (habitacionBorrada != null) {
                habitaciones.borrar(habitacionBorrada);
                System.out.println("Habitacion borrada: " + habitacionBorrada);
            } else {
                System.out.println("No se encontro ninguna habitacion con el identificador proporcionado.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error al buscar la habitaci?n: " + e.getMessage());
        }
    }

    private void mostrarHabitaciones() {
        if (habitaciones.getTamano() == 0) {
            System.out.println("No hay habitaciones almacenadas.");
        } else {
            System.out.println("Lista de habitaciones almacenadas:");
            for (Habitacion habitacion : habitaciones.get()) {
                System.out.println(habitacion.toString());
            }
        }
    }

    private void insertarReserva() {
        try {
            Reserva reservaFicticia = Consola.leerReserva();

            if (reservaFicticia == null) {
                System.out.println("No se pudo leer la reserva.");
                return;
            }

            Huesped huespedFicticio = reservaFicticia.getHuesped();
            Habitacion habitacionFicticia = reservaFicticia.getHabitacion();

            Huesped huespedReal = huespedes.buscar(huespedFicticio);
            Habitacion habitacionReal = habitaciones.buscar(habitacionFicticia);

            if (huespedReal == null) {
                throw new NoSuchElementException("El hu?sped con el DNI proporcionado no se encuentra en el sistema.");
            }

            if (habitacionReal == null) {
                throw new NoSuchElementException("La habitaci?n con el identificador proporcionado no se encuentra en el sistema.");
            }

            Reserva nuevaReserva = new Reserva(huespedReal, habitacionReal, reservaFicticia.getRegimen(),
                    reservaFicticia.getFechaInicioReserva(), reservaFicticia.getFechaFinReserva(), reservaFicticia.getNumeroPersonas());

            Habitacion habitacionDeseada = nuevaReserva.getHabitacion();
            Habitacion habitacionDisponible = consultarDisponibilidad(habitacionDeseada.getTipoHabitacion(), nuevaReserva.getFechaInicioReserva(), nuevaReserva.getFechaFinReserva());

            if (habitacionDisponible != null) {
                nuevaReserva.setHabitacion(habitacionDisponible);
                if (reservas.buscar(nuevaReserva) == null) {
                    try {
                        reservas.insertar(nuevaReserva);
                        System.out.println("Reserva insertada correctamente.");
                    } catch (OperationNotSupportedException e) {
                        System.out.println("Error al insertar la reserva: " + e.getMessage());
                    }
                } else {
                    System.out.println("La reserva ya est? registrada en el sistema.");
                }
            } else {
                System.out.println("No hay disponibilidad para el tipo de habitaci?n deseada en el periodo indicado.");
            }
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }



    private void listarReservas(Huesped huesped) {
        boolean hayReservas = false;
        for (Reserva reserva : reservas.get()) {
            if (reserva.getHuesped().equals(huesped)) {
                if (!hayReservas) {
                    System.out.println("Lista de reservas del hu?sped:");
                    hayReservas = true;
                }
                System.out.println(reserva.toString());
            }
        }
        if (!hayReservas) {
            System.out.println("No hay reservas para el hu?sped indicado.");
        }
    }

    private void listarReservas(TipoHabitacion tipoHabitacion) {
        boolean hayReservas = false;
        for (Reserva reserva : reservas.get()) {
            if (reserva.getHabitacion().getTipoHabitacion().equals(tipoHabitacion)) {
                if (!hayReservas) {
                    System.out.println("Lista de reservas para el tipo de habitaci?n " + tipoHabitacion + ":");
                    hayReservas = true;
                }
                System.out.println(reserva.toString());
            }
        }
        if (!hayReservas) {
            System.out.println("No hay reservas para el tipo de habitaci?n " + tipoHabitacion + ".");
        }
    }


    private Reserva[] getReservasAnulables(Reserva[] reservasAAnular) {
        List<Reserva> reservasAnulables = new ArrayList<>();
        LocalDate hoy = LocalDate.now(); // Obtenemos la fecha actual.

        for (Reserva reserva : reservasAAnular) {
            if (reserva.getFechaInicioReserva().isAfter(hoy)) {
                // Si la fecha de inicio de la reserva a?n no ha llegado, es anulable.
                reservasAnulables.add(reserva);
            }
        }

        // Convertimos la lista de reservas anulables a un array y lo devolvemos.
        return reservasAnulables.toArray(new Reserva[0]);
    }

    private void anularReserva() {
        try {
            System.out.println("Anular su reserva:");
            Huesped huespedFicticio = Consola.getHuespedPorDni();
            Reserva[] reservasAnulables = getReservasAnulables(reservas.getReservas(huespedFicticio));

            if (reservasAnulables.length == 0) {
                throw new NoSuchElementException("El hu?sped no tiene reservas anulables.");
            } else if (reservasAnulables.length == 1) {
                System.out.println("El hu?sped tiene una reserva anulable: " + reservasAnulables[0]);
                System.out.println("?Desea anular esta reserva? (S/N):");
                String respuesta = Entrada.cadena();
                if (respuesta.equalsIgnoreCase("S")) {
                    reservas.borrar(reservasAnulables[0]);
                    System.out.println("La reserva ha sido anulada.");
                } else {
                    System.out.println("Anulaci?n cancelada.");
                }
            } else {
                System.out.println("El hu?sped tiene varias reservas anulables:");
                for (int i = 0; i < reservasAnulables.length; i++) {
                    System.out.println((i + 1) + ".- " + reservasAnulables[i]);
                }
                System.out.println("Introduce el n?mero de la reserva que deseas anular:");
                int indice = Entrada.entero() - 1;
                if (indice >= 0 && indice < reservasAnulables.length) {
                    reservas.borrar(reservasAnulables[indice]);
                    System.out.println("La reserva seleccionada ha sido anulada.");
                } else {
                    System.out.println("N?mero de reserva no v?lido.");
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al anular la reserva: " + e.getMessage());
        }
    }



    public void mostrarReservas() {
        if (reservas.getTamano() == 0) {
            System.out.println("No hay reservas almacenadas.");
        } else {
            System.out.println("Lista de todas las reservas almacenadas:");
            for (Reserva reserva : reservas.get()) {
                System.out.println(reserva.toString());
            }
        }
    }


    private Habitacion consultarDisponibilidad(TipoHabitacion tipoHabitacion, LocalDate fechaInicio, LocalDate fechaFin) {
        for (Habitacion habitacion : habitaciones.get()) {
            if (habitacion.getTipoHabitacion().equals(tipoHabitacion)) {
                boolean estaDisponible = true;
                for (Reserva reserva : reservas.get()) {
                    if (reserva.getHabitacion().equals(habitacion) &&
                            !reserva.getFechaFinReserva().isBefore(fechaInicio) &&
                            !reserva.getFechaInicioReserva().isAfter(fechaFin)) {
                        estaDisponible = false;
                        break;
                    }
                }
                if (estaDisponible) {
                    return habitacion;
                }
            }
        }
        System.out.println("Esa habitacion no est? disponible.");
        return null;
    }

    private int getNumElementosNoNulos(Reserva[] reservas) {
        int num= 0;
        for (Reserva reserva : reservas){
            num++;
        }
        return num;
    }

    private void realizarCheckin()
    {
        Huesped huesped = Consola.getHuespedPorDni();
        Reserva[] reservas = controlador.getReservas(huesped);
        LocalDateTime fechaCheckin = Consola.leerFechaHora("Introduce la fecha y hora del checkin");

        int checkInRealizados = 0;
        for (int i = 0; i < reservas.length; i++)
        {
            if (reservas[i] != null && reservas[i].getFechaInicioReserva().equals(fechaCheckin.toLocalDate()))
            {
                controlador.realizarCheckin(reservas[i], fechaCheckin);
                checkInRealizados++;
            }
        }

        if(checkInRealizados == 0)
        {
            System.out.println("No existe reserva para la fecha indicado");
        }
    }

    private void realizarCheckout()
    {
        Huesped huesped = Consola.getHuespedPorDni();
        Reserva[] reservas = controlador.getReservas(huesped);
        LocalDateTime fechaCheckout = Consola.leerFechaHora("Introduce la fecha y hora del checkout");

        int checkOutRealizados = 0;
        for (int i = 0; i < reservas.length; i++)
        {
            if (reservas[i] != null && reservas[i].getFechaFinReserva().equals(fechaCheckout.toLocalDate()))
            {
                controlador.realizarCheckout(reservas[i], fechaCheckout);
                checkOutRealizados++;
            }
        }

        if(checkOutRealizados == 0)
        {
            System.out.println("No existe reserva para la fecha indicado");
        }
    }

}
