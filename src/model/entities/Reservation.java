/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import model.exceptions.DomainException;

/**
 *
 * @author Rodrigo
 */
public class Reservation {

    //Variaveris da classe
    private Integer roomNumber;
    private Date checkIn;
    private Date checkOut;

    // variavel sdf instanciando um formato de data 
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    // metodo construtor padrão
    public Reservation() {
    }

    // metodo construdor com argumentos
    public Reservation(Integer roomNumber, Date checkIn, Date checkOut) throws DomainException{
        if(!checkOut.after(checkIn)){
            throw new DomainException("Check-out date must be after check-in date");
        }
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    // get e set da variavel numero do quarto
    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    // apenas gets das variaveis check in e check out
    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    // diff recebe a diferença de tempo entre checkOut e checkIn, necessário converter 
    // o resultado armazenado em diff que está em milesegundos para dias e retornar.
    public long duration() {
        long diff = checkOut.getTime() - checkIn.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    //metodo para atualizar check in e check out
    public void updateDates(Date checkIn, Date checkOut)throws DomainException {

        Date now = new Date();
        if (checkIn.before(now) || checkOut.before(now)) {
            throw new DomainException ("Error in reservation: Reservation dates for update must be future");
        }
        if (!checkOut.after(checkIn)) {
            throw new DomainException ("Error in reservation: Check-out date must be after check-in date");
        }
        
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    @Override
    public String toString() {
        return "Room "
                + roomNumber
                + ", Check-in: "
                + sdf.format(checkIn)
                + " ,Check-out: "
                + sdf.format(checkOut)
                + " , "
                + duration()
                + " nights ";
    }

}
