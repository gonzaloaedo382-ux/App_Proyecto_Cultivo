package com.example.cultivos_orquideas;
import java.util.ArrayList;
import java.util.List;

public class LibreriaPlantas {

    private static List<Planta> listaPlantas = new ArrayList<>();

    static {
        listaPlantas.add(new Planta(
                "Orquídea Phalaenopsis",
                "Phalaenopsis amabilis",
                "Luz indirecta brillante",
                "Cada 7-10 días",
                "Saludable",
                R.drawable.phalaenopsis
        ));

        listaPlantas.add(new Planta(
                "Orquídea Cattleya",
                "Cattleya labiata",
                "Mucha luz sin sol directo",
                "Cada 10-14 días",
                "En floración",
                R.drawable.cattleya
        ));

        listaPlantas.add(new Planta(
                "Orquídea Dendrobium",
                "Dendrobium nobile",
                "Luz filtrada abundante",
                "Cada 5-7 días",
                "Requiere atención",
                R.drawable.dendrobium
        ));
        listaPlantas.add(new Planta(
                "Orquídea Dendrobium",
                "Dendrobium nobile",
                "Luz filtrada abundante",
                "Cada 5-9 días",
                "Requiere atención",
                R.drawable.
        ));
    }
    public static Planta obtenerPlantaPorNombre(String nombreBuscado) {
        if (nombreBuscado == null) return null;
        for (Planta p : listaPlantas) {
            if (p.getNombre().equalsIgnoreCase(nombreBuscado)) {
                p.getImagenResId();
                return p;
            }
        }
        return listaPlantas.get(0);
    }
}