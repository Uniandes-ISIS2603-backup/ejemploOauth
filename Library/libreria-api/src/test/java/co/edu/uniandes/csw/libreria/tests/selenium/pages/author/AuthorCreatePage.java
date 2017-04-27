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
package co.edu.uniandes.csw.libreria.tests.selenium.pages.author;

import co.edu.uniandes.csw.libreria.dtos.minimum.AuthorDTO;
import static org.jboss.arquillian.graphene.Graphene.guardAjax;
import static org.jboss.arquillian.graphene.Graphene.waitGui;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthorCreatePage {

    @FindBy(id = "nombre")
    private WebElement nombreInput;
    @FindBy(id = "descripcion")
    private WebElement descripcionInput;

    @FindBy(id = "save-author")
    private WebElement saveBtn;

    @FindBy(id = "cancel-author")
    private WebElement cancelBtn;

    public void saveAuthor(AuthorDTO author) {
         waitGui().until().element(nombreInput).is().visible();
         nombreInput.clear();
         nombreInput.sendKeys(author.getNombre());
         waitGui().until().element(descripcionInput).is().visible();
         descripcionInput.clear();
         descripcionInput.sendKeys(author.getDescripcion());
        guardAjax(saveBtn).click();
    }
}
