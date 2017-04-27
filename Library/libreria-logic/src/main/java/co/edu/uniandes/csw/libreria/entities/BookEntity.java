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
package co.edu.uniandes.csw.libreria.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.CascadeType;


/**
 * @generated
 */
@Entity
public class BookEntity extends BaseEntity implements Serializable {

    private String nombre;

    private String foto;

    @PodamExclude
    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
    private List<ReviewEntity> review = new ArrayList<>();

    /**
     * Obtiene el atributo nombre.
     *
     * @return atributo nombre.
     * @generated
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Establece el valor del atributo nombre.
     *
     * @param nombre nuevo valor del atributo
     * @generated
     */
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    /**
     * Obtiene el atributo foto.
     *
     * @return atributo foto.
     * @generated
     */
    public String getFoto(){
        return foto;
    }

    /**
     * Establece el valor del atributo foto.
     *
     * @param foto nuevo valor del atributo
     * @generated
     */
    public void setFoto(String foto){
        this.foto = foto;
    }

    /**
     * Obtiene la colección de review.
     *
     * @return colección review.
     * @generated
     */
    public List<ReviewEntity> getReview() {
        return review;
    }

    /**
     * Establece el valor de la colección de review.
     *
     * @param review nuevo valor de la colección.
     * @generated
     */
    public void setReview(List<ReviewEntity> review) {
        this.review = review;
    }
}
