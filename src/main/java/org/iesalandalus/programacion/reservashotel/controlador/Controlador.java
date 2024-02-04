package org.iesalandalus.programacion.reservashotel.controlador;

import org.iesalandalus.programacion.reservashotel.modelo.Modelo;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.vista.Vista;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDateTime;

public class Controlador {


    private Modelo modelo;
    private Vista vista;



    public Controlador(Modelo modelo,Vista vista)
    {
        if (modelo==null || vista == null)
        {
            throw new NullPointerException("El controlador o la vista no pueden ser nulos.");
        }
        else
        {
            this.modelo=modelo;
            this.vista=vista;

            this.vista.setControlador(this);
        }
    }

    public void comenzar()
    {
        modelo.comenzar();
        vista.comenzar();
    }

    public void terminar()
    {
        modelo.terminar();
        vista.terminar();
    }


    public void insertar (Huesped huesped) throws OperationNotSupportedException
    {
        modelo.insertar(huesped);

    }

    public void insertar (Habitacion habitacion) throws OperationNotSupportedException
    {
        modelo.insertar(habitacion);
    }

    public void insertar (Reserva reserva) throws OperationNotSupportedException
    {
        modelo.insertar(reserva);
    }


    public Huesped buscar(Huesped huesped)
    {
        return modelo.buscar(huesped);
    }

    public Habitacion buscar(Habitacion habitacion)
    {
        return modelo.buscar(habitacion);
    }

    public Reserva buscar(Reserva reserva)
    {
        return modelo.buscar(reserva);
    }



    public void borrar(Huesped huesped) throws OperationNotSupportedException
    {
        modelo.borrar(huesped);
    }

    public void borrar(Habitacion habitacion) throws OperationNotSupportedException
    {
        modelo.borrar(habitacion);
    }

    public void borrar(Reserva reserva) throws OperationNotSupportedException
    {
        modelo.borrar(reserva);
    }



    public Huesped[] getHuespedes()
    {
        return modelo.getHuespedes();
    }

    public Habitacion[]getHabitaciones()
    {
        return modelo.getHabitaciones();
    }

    public Habitacion[] getHabitaciones(TipoHabitacion tipoHabitacion)
    {
        return modelo.getHabitaciones(tipoHabitacion);
    }


    public Reserva[] getReservas()
    {
        return modelo.getReservas();
    }

    public Reserva[]getReservas(Huesped huesped )
    {
        return modelo.getReservas(huesped);
    }

    public Reserva[] getReservas(TipoHabitacion tipohabitacion)
    {
        return modelo.getReservas(tipohabitacion);
    }

    public Reserva[] getReservasFuturas(Habitacion habitacion)
    {
        return modelo.getReservasFuturas(habitacion);
    }



    public void realizarCheckin(Reserva reserva, LocalDateTime fecha)
    {
        modelo.realizarCheckin(reserva, fecha);
    }

    public void realizarCheckout(Reserva reserva, LocalDateTime fecha)
    {
        modelo.realizarCheckout(reserva,fecha);
    }

}

