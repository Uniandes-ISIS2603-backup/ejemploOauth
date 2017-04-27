/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package co.edu.uniandes.csw.libreria.dtos.minimum;

import co.edu.uniandes.csw.libreria.entities.ReviewEntity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @generated
 */
@XmlRootElement
public class ReviewDTO  implements Serializable{

    private String descripcion;
    private String autor;


    /**
     * @generated
     */
    public ReviewDTO() {
    }

    /**
     * Crea un objeto ReviewDTO a partir de un objeto ReviewEntity.
     *
     * @param entity Entidad ReviewEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public ReviewDTO(ReviewEntity entity) {
	   if (entity!=null){
        this.descripcion=entity.getDescripcion();
        this.autor=entity.getAutor();
       }
    }

    /**
     * Convierte un objeto ReviewDTO a ReviewEntity.
     *
     * @return Nueva objeto ReviewEntity.
     * @generated
     */
    public ReviewEntity toEntity() {
        ReviewEntity entity = new ReviewEntity();
        entity.setDescripcion(this.getDescripcion());
        entity.setAutor(this.getAutor());
    return entity;
    }

    /**
     * Obtiene el atributo descripcion.
     *
     * @return atributo descripcion.
     * @generated
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece el valor del atributo descripcion.
     *
     * @param descripcion nuevo valor del atributo
     * @generated
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el atributo autor.
     *
     * @return atributo autor.
     * @generated
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Establece el valor del atributo autor.
     *
     * @param autor nuevo valor del atributo
     * @generated
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }


}
