package espol.edu.ec.TDAs;

import espol.edu.ec.TDAs.Vuelo;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Aerolinea {

    private String ruc, razonSocial, nombreComercial;

    private LinkedList<Vuelo> vuelos;
//CONSTRUCTOR

    public Aerolinea(String ruc, String rs, String nc) {
        this.ruc = ruc;
        razonSocial = rs;
        nombreComercial = nc;

        vuelos = new LinkedList<>();
    }

    //M�TODO QUE CARGA TODA LA INFORMACI�N DEL ARCHIVO Y LAS CONVIERTE A ONJETOS DE TIPO AEROL�NEA, QUE SON INGRESADOS A UNA LISTA ENLAZADA
    public static LinkedList<Aerolinea> cargarPorArchivo(String archivo) {
        LinkedList<Aerolinea> listaAerolinea = new LinkedList<>();
        String linea;
        try {
            File file = new File(archivo);
            BufferedReader bf = new BufferedReader(new FileReader(file));
            while ((linea= bf.readLine()) != null) {
                System.out.println(linea);
                String[] cadenas = linea.split("\\|");
                Aerolinea aerolinea = new Aerolinea(cadenas[0], cadenas[1], cadenas[2]);
                listaAerolinea.add(aerolinea);
            }
            bf.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("No se pudo encontrar el archivo");
            return listaAerolinea;
        } catch (IOException ex) {
            Logger.getLogger(Aerolinea.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaAerolinea;

    }

//M�TODO QUE BUSCA UNA AEROL�NEA POR RUC (BUSCA DENTRO DE LA LISTA DE AEROL�NEA)
    public static Aerolinea buscarPorRuc(LinkedList<Aerolinea> L, String ruc) {
        ListIterator<Aerolinea> i = L.listIterator();
        while (i.hasNext()) {
            Aerolinea a = i.next();
            if (a.getRuc().equals(ruc)) {
                return a;
            }
        }
        return null;
    }

    //M�todo que refresca todo el archivo de aerol�neas a partir de una lista enlazada
    public static boolean cargarAlArchivo(LinkedList<Aerolinea> listaAerolineas, String archivo) {

        try {
            //M�todo para escribir en un archivo (reescribe)
            File file = new File(archivo);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            ListIterator<Aerolinea> i = listaAerolineas.listIterator();
            while (i.hasNext()) {
                Aerolinea a = i.next();
                bw.write(a.aString());
                bw.write("\n");
            }
            bw.close();
            return true;

        } catch (Exception ex) {
            System.out.println("No se pudo escribir en el archivo");
            Logger.getLogger(Vuelo.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    
    //m�todo que retorna los datos de la aerolinea en string, para ingresarlos al archivo
    public String aString() {
        return this.ruc + "|" + this.razonSocial + "|" + this.nombreComercial;
    }

    //m�todo equals
    public boolean equals(Object o1) {
        if (o1 instanceof Aerolinea) {
            Aerolinea aerolinea1 = (Aerolinea) o1;
            return this.ruc.equals(aerolinea1.getRuc());

        } else {
            return false;
        }
    }

    public static boolean aerolineaEnLista(LinkedList<Aerolinea> lAerolineas, String ruc) {

        ListIterator<Aerolinea> i = lAerolineas.listIterator();
        while (i.hasNext()) {
            Aerolinea a = i.next();
            if (a.getRuc().equals(ruc)) {
                return true;
            }
        }
        return false;
    }
//GETTERS Y SETTERS

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public LinkedList<Vuelo> getVuelos() {
        return vuelos;
    }

    public void setVuelos(LinkedList<Vuelo> vuelos) {
        this.vuelos = vuelos;
    }

    //MODIFICA LOS DATOS DE UNA AEROL�NEA
    public void modificarAerolinea(Aerolinea aerolinea, String ruc, String razonSocial, String nombreComercial) {
        aerolinea.setRuc(ruc);
        aerolinea.setRazonSocial(razonSocial);
        aerolinea.setNombreComercial(nombreComercial);

    }
    //M�TODO QUE DEVUELVE LA INFORMACI�N DEL OBJETO CON EL FORMATO PERMITENTE PARA ESCRIBIR EN EL ARCHIVO

    public String toString() {
        return "Nombre Aerol�nea: " + this.nombreComercial + " Ruc: " + this.ruc;
    }

}
