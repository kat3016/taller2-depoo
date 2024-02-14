package uniandes.dpoo.estructuras.logica;

import java.util.Collection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import java.util.HashSet;


/***
 * Esta clase tiene un conjunto de métodos para practicar operaciones sobre mapas.
 *
 * Todos los métodos deben operar sobre el atributo mapaCadenas que se declara como un Map.
 * 
 * En este mapa, las llaves serán cadenas y los valores serán también cadenas. La relación entre los dos será que cada llave será igual a la cadena del valor, pero invertida.
 * 
 * El objetivo de usar el tipo Map es que sólo puedan usarse métodos de esa interfaz y no métodos adicionales provistos por la implementación concreta (HashMap).
 * 
 * No pueden agregarse nuevos atributos.
 */
public class SandboxMapas
{
    /**
     * Un mapa de cadenas para realizar varias de las siguientes operaciones.
     * 
     * Las llaves del mapa son cadenas, así como los valores.
     * 
     * Las llaves corresponden a invertir la cadena que aparece asociada a cada llave.
     */
    private Map<String, String> mapaCadenas;

    /**
     * Crea una nueva instancia de la clase con las dos listas inicializadas pero vacías
     */
    public SandboxMapas( )
    {
        mapaCadenas = new HashMap<String, String>( );
    }

    /**
     * Retorna una lista con las cadenas del mapa (los valores) ordenadas lexicográficamente
     * @return Una lista ordenada con las cadenas que conforman los valores del mapa
     */
    public List<String> getValoresComoLista( )
    {
    	ArrayList<String> values = new ArrayList<>(mapaCadenas.values());
        values.sort(String::compareTo); 
        return values; 
    }

    /**
     * Retorna una lista con las llaves del mapa ordenadas lexicográficamente de mayor a menor
     * @return Una lista ordenada con las cadenas que conforman las llaves del mapa
     */
    public List<String> getLlavesComoListaInvertida( )
    {
    	ArrayList<String> keys = new ArrayList<>(mapaCadenas.keySet());
        Collections.sort(keys, Collections.reverseOrder());
        return keys;
    }

    /**
     * Retorna la cadena que sea lexicográficamente menor dentro de las llaves del mapa .
     * 
     * Si el mapa está vacío, debe retornar null.
     * @return
     */
    public String getPrimera( )
    {
    	if (mapaCadenas.isEmpty()) 
    	{
    		return null; 
    	}
    	List<String> keys = new ArrayList<>(mapaCadenas.keySet()); 
        Collections.sort(keys);
        return keys.get(0) ; 
    }

    /**
     * Retorna la cadena que sea lexicográficamente mayor dentro de los valores del mapa
     * 
     * Si el conjunto está vacío, debe retornar null.
     * @return
     */
    public String getUltima( )
    {
    	if (mapaCadenas.isEmpty())
    	{
    		return null;
    	} 
    	
    	List<String> keys = new ArrayList<>(mapaCadenas.keySet()); 
        Collections.sort(keys);
        int tamanio=keys.size()-1;
        return keys.get(tamanio);
    }

    /**
     * Retorna una colección con las llaves del mapa, convertidas a mayúsculas.
     * 
     * El orden de las llaves retornadas no importa.
     * @return Una lista de cadenas donde todas las cadenas están en mayúsculas
     */
    public Collection<String> getLlaves( )
    {
    	Set<String> keys = new HashSet<>();
        
        for (String key: mapaCadenas.keySet())
        {
            keys.add(key.toUpperCase());
        }

        return new ArrayList<>(keys);
    }

    /**
     * Retorna la cantidad de *valores* diferentes en el mapa
     * @return
     */
    public int getCantidadCadenasDiferentes( )
    {
ArrayList<String> unicosvalues = new ArrayList<>();
        
        for (String value : mapaCadenas.values())
        {
            if (! unicosvalues.contains(value)) 
            {
                unicosvalues.add(value);
            }
        }
        
        return unicosvalues.size();
        }
   


    /**
     * Agrega un nuevo valor al mapa de cadenas: el valor será el recibido por parámetro, y la llave será la cadena invertida
     * 
     * Este método podría o no aumentar el tamaño del mapa, dependiendo de si ya existía la cadena en el mapa
     * 
     * @param cadena La cadena que se va a agregar al mapa
     */
    public void agregarCadena( String cadena )
    {
    	String key = new StringBuilder(cadena).reverse().toString();
        mapaCadenas.put(key, cadena);
    }

    /**
     * Elimina una cadena del mapa, dada la llave
     * @param cadena La llave para identificar el valor que se debe eliminar
     */
    public void eliminarCadenaConLLave( String llave)
    {
    	if (mapaCadenas.containsKey(llave));
    	{
    		mapaCadenas.remove(llave); 
    	}   	
    }

    /**
     * Elimina una cadena del mapa, dado el valor
     * @param cadena El valor que se debe eliminar
     */
    public void eliminarCadenaConValor( String valor )
    {
    	Iterator<Map.Entry<String, String>> iterator = mapaCadenas.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, String> entrada = iterator.next();
            if (entrada.getValue().equals(valor)) {
                iterator.remove();
            }
        }
    }

    /**
     * Reinicia el mapa de cadenas con las representaciones como Strings de los objetos contenidos en la lista del parámetro 'objetos'.
     * 
     * Use el método toString para convertir los objetos a cadenas.
     * @param valores Una lista de objetos
     */
    public void reiniciarMapaCadenas( List<Object> objetos )
    {
    	mapaCadenas.clear();

        for (Object obj : objetos)
        {
            String cadena = obj.toString();
            String key = Integer.toHexString(System.identityHashCode(obj));
            mapaCadenas.put(key, cadena);
        }
    }
    

    /**
     * Modifica el mapa de cadenas reemplazando las llaves para que ahora todas estén en mayúsculas pero sigan conservando las mismas cadenas asociadas.
     */
    public void volverMayusculas( )
    {
    	Map<String, String> newmap = new HashMap<>();

        for (Map.Entry<String, String> entrada : mapaCadenas.entrySet()) {
            String llave = entrada.getKey(); 
            String valor = entrada.getValue(); 

            String keyMayuscula = llave.toUpperCase();
            newmap.put(keyMayuscula, valor);
        }

        
        mapaCadenas = newmap;
    }

    /**
     * Verifica si todos los elementos en el arreglo de cadenas del parámetro hacen parte del mapa de cadenas (de los valores)
     * @param otroArreglo El arreglo de enteros con el que se debe comparar
     * @return True si todos los elementos del arreglo están dentro de los valores del mapa
     */
    public boolean compararValores( String[] otroArreglo )
    {
    	for (String ele : otroArreglo) {
            if (!mapaCadenas.containsValue(ele)) {
                return false; 
            }
        }
        return true;
    }

}
