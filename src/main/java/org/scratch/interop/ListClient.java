package org.scratch.interop;

import java.util.Iterator;
import scala.collection.JavaConversions;

public class ListClient {
    public static void main(String[] args) {
        System.out.println("Scala list client");

        String ent = null;
        for( Iterator<String> it =  JavaConversions.asJavaIterable( ListServer.getList() ).iterator(); it.hasNext(); ){
            ent=it.next();
            System.out.println(ent);
        }
    }
}
