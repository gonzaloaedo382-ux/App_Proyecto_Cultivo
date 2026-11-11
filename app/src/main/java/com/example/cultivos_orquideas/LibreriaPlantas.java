package com.example.cultivos_orquideas;
import java.util.ArrayList;
import java.util.List;

public class LibreriaPlantas {
    private static List<Planta> listaPlantas = new ArrayList<>();

    /* se realiza una busqueda aqui una ves sacada la foto y verifica si hay coincidencias */
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
                "Tomatera",
                "Tomatera normal",
                "Luz filtrada abundante",
                "Cada 5-9 días",
                "Requiere atención",
                R.drawable.Tomatera
        ));

        listaPlantas.add(new Planta(
                "Helecho",
                "Helecho normal",
                "Luz filtrada abundante",
                "Cada 3-5 días",
                "No Requiere mucha atención",
                R.drawable.Helecho
        ));
        listaPlantas.add(new Planta(
                "Palto",
                "Palto normal",
                "Luz filtrada abundante",
                "Cada 4-7 días",
                "Requiere atención",
                R.drawable.Palto
        ));
        listaPlantas.add(new Planta(
                "Calendula",
                "Calendula normal",
                "Luz filtrada abundante",
                "Cada 3-8 días",
                "En Floracion",
                R.drawable.Calendula
        ));
        listaPlantas.add(new Planta(
                "Pasiflora",
                "Pasiflora libiata",
                "Mucha luz sin sol directo",
                "Cada 6-10 días",
                "Requiere atención",
                R.drawable.Pasiflora
        ));
        listaPlantas.add(new Planta(
                "Cayena",
                "Cayena normal",
                "Luz filtrada abundante",
                "Cada 5-9 días",
                "En Floracion",
                R.drawable.Cayena
        ));
        listaPlantas.add(new Planta(
                "Diente de Leon",
                "Diente de leon Comun",
                "Luz filtrada abundante",
                "Cada 4-8 días",
                "Requiere atención",
                R.drawable.Diente_de_leon
        ));
    }
    /* se muestra la planta encontrada en la biblioteca buscada por el nombre */
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