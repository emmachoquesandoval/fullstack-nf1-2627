package nf1.fullstack.lambdes_streams;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ExerciciL {

    public static void main(String[] args) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyy");
        Persona p1 = new Persona("Arya", Persona.Genere.DONA, LocalDate.parse("25/12/2002",format) );
        Persona p2 = new Persona("Tyrion", Persona.Genere.HOME, LocalDate.parse("12/10/1980",format));
        Persona p3 = new Persona("Cersei", Persona.Genere.DONA, LocalDate.parse("10/01/1984",format));
        Persona p4 = new Persona("Eddard", Persona.Genere.HOME, LocalDate.parse("24/04/1974",format));
        Persona p5 = new Persona("Sansa", Persona.Genere.DONA, LocalDate.parse("24/04/1992",format));
        Persona p6 = new Persona("Jaime", Persona.Genere.HOME, LocalDate.parse("24/04/1979",format));
        Persona p7 = new Persona("Khal", Persona.Genere.HOME, LocalDate.parse("10/08/1979",format));
        Persona p8 = new Persona("Daenerys", Persona.Genere.DONA, LocalDate.parse("12/11/1992",format));
        Persona p9 = new Persona("Davos", Persona.Genere.HOME, LocalDate.parse("12/11/1965",format));
        Persona p10 = new Persona("Jon Neu", Persona.Genere.HOME, LocalDate.parse("12/11/1986",format));
        Persona p11 = new Persona("Brienne", Persona.Genere.DONA, LocalDate.parse("12/11/1989",format));

        Persona[] lpers = {p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11};
        List<Persona> llistaPersones;
        llistaPersones = new ArrayList<>(Arrays.asList(lpers));
        Map<Integer,Integer> mapPersones = new HashMap<>();

        // 1 - Cambiar por una lambda

        System.out.println("\n1-2");

        /*Collections.sort(llistaPersones, new Comparator<Persona>() {
            @Override
            public int compare(Persona o1, Persona o2) {
                if(o1.getNom().charAt(0) >= o2.getNom().charAt(0)) return 1;
                else return -1;
            }
        });*/
        Collections.sort(llistaPersones, (o1,o2) -> o1.getNom().charAt(0) >= o2.getNom().charAt(0) ? 1 : -1);
        llistaPersones.forEach(System.out::println);


        // 2 - Cambiar por una lambda
        /*for(Persona p: llistaPersones) {
            System.out.println(p);
        }*/
        llistaPersones.forEach(System.out::println);


        // 3 - Cambia por una clase anónima
        System.out.println("\n3-4");
        //ordenación alfabética inversa del nombre
        /*    llistaPersones.sort((o1,o2) -> o2.getNom().compareTo(o1.getNom()));*/
        llistaPersones.sort(new Comparator<Persona>() {
            @Override
            public int compare(Persona o1, Persona o2) {
                return o2.getNom().compareTo(o1.getNom());
            }
        });


        // 4 - Cambia por una llamada al método mediante una referencia
        /*for(Persona p: llistaPersones) {
            System.out.println(p);
        };*/
        System.out.println("\n4");
        llistaPersones.forEach(System.out::println);


        // 5 - Cambia el llenado de este map por un forEach con lambda
        /*for(Persona per : llistaPersones) {
            mapPersones.put(per.getAge(),1);
        }*/
        System.out.println("\n5");
        llistaPersones.forEach(per -> mapPersones.put(per.getAge(),1));


        // 6 - Cambia este bucle for por un recorrido forEach con lambda
        System.out.println("\n5");
        /*for(Map.Entry entry : mapPersones.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }*/
        mapPersones.forEach((k,v) -> System.out.println(k + " : " + v));

        // 7 - Obtén un listado de personas de género DONA con lambda y stream
        System.out.println("\n7 DONES");
        llistaPersones.stream()
                .filter(persona -> persona.getGenere().equals("DONA"))
                .forEach(System.out::println);

        // 8 - Obtén un listado de los dos hombres más jóvenes (stream)
        System.out.println("\n8 HOMES + JOVES");

        llistaPersones.stream()
                .filter(persona -> persona.getGenere().equals("HOME"))
                .sorted(Comparator.comparingInt(Persona::getAge))
                .limit(2)
                .forEach(System.out::println);

        // 9- Eliminar de llistaPersones las personas de entre 30 y 40 años (lambda)
        System.out.println("\n9");

        llistaPersones.removeIf(persona -> persona.getAge() >= 30 && persona.getAge() <= 40);
        llistaPersones.forEach(System.out::println);

        // 10 - Obtén un listado de las personas que tengan una 'a' en su nombre
        System.out.println("\n10 Amb una 'A'");

        llistaPersones.stream()
                .filter(persona -> persona.getNom().toLowerCase().contains("a"))
                .forEach(System.out::println);

        //11 - Obtén un listado con las fechas de nacimiento de las personas más dos días
        System.out.println("\n11 - dates amb dos dies més");

        llistaPersones.stream()
                .map(persona -> persona.getDataNaixament().plusDays(2))
                .forEach(System.out::println);

        //12 - Rejuvenecer (modificar) dos años a todas las personas
        System.out.println("\n12 - Rejovenir dos anys a totes les persones");

        llistaPersones.stream()
                .map(persona -> persona.getDataNaixament().plusYears(2))
                .forEach(System.out::println);

        //13. A partir de la siguiente lista de nombres de alumnos, crea una nueva lista de alumnos con estos alumnos nuevos
        // Puedes modificar la clase Alumne si es necesario.
        System.out.println("\n13 - Crear una nova llista d'alumnes");
        List<String> alumnesNous = Arrays.asList("Pedro","Pablo","Bilma");
        alumnesNous.stream()
                .map(alumne -> new Alumne(alumne));
        /*.forEach(alumne -> System.out.println(alumne.getNom()+" "+alumne.getDataNaixament()));*/
        /*.forEach(System.out::println)*/
        alumnesNous.stream().forEach(System.out::println);

        //14. Con un stream, muestra las personas nacidas antes del año 1980.
        // Utiliza una referencia a método para mostrar cada persona.
        System.out.println("\n14 - Persones nascudes abans de 1980");
        llistaPersones.stream()
                .filter(persona -> persona.getDataNaixament().getYear() < 1980)
                .forEach(System.out::println);

        //15. Crea y muestra una lista de nombres de las personas, ordenada alfabéticamente.
        // Usa map y referencias a métodos.
        System.out.println("\n15 - Noms ordenats");
        llistaPersones.stream()
                .map(Persona::getNom)
                .sorted()
                .forEach(System.out::println);

        //16. Con un stream, muestra las edades de las personas cuyo nombre tiene más de cinco letras.
        // Utiliza una referencia a método para mostrar cada edad.
        System.out.println("\n16 - Edats de persones amb noms llargs");
        llistaPersones.stream()
                .filter(persona -> persona.getNom().length() > 5)
                .map(Persona::getDataNaixament)
                .forEach(System.out::println);

        //17. Muestra los nombres de las mujeres en mayúsculas.
        // Resuélvelo con filter, map y una referencia a método.
        System.out.println("\n17 - Noms de les dones en majúscules");
        llistaPersones.stream()
                .filter(persona -> persona.getGenere().equals("Dona"))
                .map(Persona::getNom)
                .map(String::toUpperCase)
                .forEach(System.out::println);

        //18. Cuenta cuántas personas nacieron en noviembre utilizando un stream.
        System.out.println("\n18 - Persones nascudes al novembre");
        llistaPersones.stream()
                .filter(persona -> persona.getDataNaixament().getMonthValue() == 11)
                .count();

        //19. Busca y muestra la persona más joven con min y Comparator.comparingInt.
        // Usa una referencia a método siempre que sea posible.
        System.out.println("\n19 - Persona més jove");
        llistaPersones.stream()
                .min(Comparator.comparing(Persona::getDataNaixament))
                .ifPresent(System.out::println);

        //20. Convierte alumnesNous en una lista de objetos Alumne con un stream
        // y Alumne::new, ordénalos por nombre y muéstralos con una referencia a método.
        System.out.println("\n20 - Alumnes ordenats pel nom");
        llistaPersones.stream()
                .sorted(Comparator.comparing(Persona::getNom))
                .forEach(System.out::println);

        //21. A partir de esta lista de marcas, crea una lista de Cotxe
        // usando un stream y la referencia al constructor Cotxe::new.
        // Muestra los coches con una referencia a método.
        List<String> marquesCotxes = Arrays.asList("Seat", "Toyota", "Ford", "Tesla");
        System.out.println("\n21 - Crear cotxes a partir de marques");
        List<Cotxe> cotxes = marquesCotxes.stream()
                .map(Cotxe::new)
                .toList();
        cotxes.forEach(System.out::println);

        //22. Asigna a las primeras personas de llistaPersones los Cotxe creados
        // en el ejercicio anterior. Haz la asignación con un stream y una lambda,
        // y muestra después las marcas de los coches asignados.
        System.out.println("\n22 - Assignar cotxes a les persones");
        llistaPersones.stream()
                .limit(cotxes.size())
                .forEach(persona -> persona.setCotxe(cotxes.get(llistaPersones.indexOf(persona))));
        llistaPersones.stream()
                .filter(persona -> persona.getCotxe() != null)
                .map(Persona::getCotxe)
                .map(Cotxe::getMarca)
                .forEach(System.out::println);
    }


}
