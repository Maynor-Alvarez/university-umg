/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.logina;

import java.util.ArrayList;

/**
 *
 * @author maynor
 */
public class LoginA {
    public static ArrayList<Profesor> profesores = new ArrayList<>();
    public static Profesor profesorLogeado = new Profesor();
    public static ArrayList<Alumno> alumnos = new ArrayList<>();
    public static Alumno alumnoLogeado = new Alumno();
    public static ArrayList<Cursos> curso= new ArrayList<>();
    public static int conta=0;
    
    public static void main(String[] args) {
        LoginF l = new LoginF();
        l.setVisible(true);
        }

}
