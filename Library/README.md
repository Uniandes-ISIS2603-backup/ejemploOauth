# Tabla de contenidos
-  [Introducción](#introducción)
-  [API](#api-de-la-aplicación-libreria)
  - [Recurso Author](#recurso-author)
    - [GET /authors](#GET-/authors)
    - [GET /authors/{id}](#GET-/authors/{id})
    - [POST /authors](#POST-/authors)
    - [PUT /authors/{id}](#PUT-/authors/{id})
    - [DELETE /authors/{id}](#DELETE-/authors/{id})
    - [GET authors/{authorsid}/prize](#GET-authors/{authorsid}/prize)
    - [GET authors/{authorsid}/prize/{prizeid}](#GET-authors/{authorsid}/prize/{prizeid})
    - [POST authors/{authorsid}/prize/{prizeid}](#POST-authors/{authorsid}/prize/{prizeid})
    - [PUT authors/{authorsid}/prize](#PUT-authors/{authorsid}/prize)
    - [DELETE authors/{authorsid}/prize/{prizeid}](#DELETE-authors/{authorsid}/prize/{prizeid}])
  - [Recurso Book](#recurso-book)
    - [GET /books](#GET-/books)
    - [GET /books/{id}](#GET-/books/{id})
    - [POST /books](#POST-/books)
    - [PUT /books/{id}](#PUT-/books/{id})
    - [DELETE /books/{id}](#DELETE-/books/{id})
    - [GET books/{booksid}/review](#GET-books/{booksid}/review)
    - [GET books/{booksid}/review/{reviewid}](#GET-books/{booksid}/review/{reviewid})
    - [POST books/{booksid}/review/{reviewid}](#POST-books/{booksid}/review/{reviewid})
    - [PUT books/{booksid}/review](#PUT-books/{booksid}/review)
    - [DELETE books/{booksid}/review/{reviewid}](#DELETE-books/{booksid}/review/{reviewid}])
  - [Recurso Prize](#recurso-prize)
    - [GET /prizes](#GET-/prizes)
    - [GET /prizes/{id}](#GET-/prizes/{id})
    - [POST /prizes](#POST-/prizes)
    - [PUT /prizes/{id}](#PUT-/prizes/{id})
    - [DELETE /prizes/{id}](#DELETE-/prizes/{id})

# API Rest
## Introducción
La comunicación entre cliente y servidor se realiza intercambiando objetos JSON. Para cada entidad se hace un mapeo a JSON, donde cada uno de sus atributos se transforma en una propiedad de un objeto JSON. Todos los servicios se generan en la URL /Libreria.api/api/. Por defecto, todas las entidades tienen un atributo `id`, con el cual se identifica cada registro:

```javascript
{
    id: '',
    attribute_1: '',
    attribute_2: '',
    ...
    attribute_n: ''
}
```

Cuando se transmite información sobre un registro específico, se realiza enviando un objeto con la estructura mencionada en la sección anterior.
La única excepción se presenta al solicitar al servidor una lista de los registros en la base de datos, que incluye información adicional para manejar paginación de lado del servidor en el header `X-Total-Count` y los registros se envían en el cuerpo del mensaje como un arreglo.

La respuesta del servidor al solicitar una colección presenta el siguiente formato:

```javascript
[{}, {}, {}, {}, {}, {}]
```

## API de la aplicación Libreria
### Recurso Author
El objeto Author tiene 2 representaciones JSON:	

#### Representación Minimum
```javascript
{
    id: '' /*Tipo Long*/,
    nombre: '' /*Tipo String*/,
    descripcion: '' /*Tipo String*/
}
```




#### GET /authors

Retorna una colección de objetos Author en representación Detail.

#### Parámetros

#### N/A

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detail](#recurso-author)
409|Un objeto relacionado no existe|Mensaje de error
500|Error interno|Mensaje de error

#### GET /authors/{id}

Retorna una colección de objetos Author en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Author a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Author en [representaciones Detail](#recurso-author)
404|No existe un objeto Author con el ID solicitado|Mensaje de error
500|Error interno|Mensaje de error

#### POST /authors

Es el encargado de crear objetos Author.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|Objeto Author que será creado|Sí|[Representación Detail](#recurso-author)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Author ha sido creado|[Representación Detail](#recurso-author)
409|Un objeto relacionado no existe|Mensaje de error
500|No se pudo crear el objeto Author|Mensaje de error

#### PUT /authors/{id}

Es el encargado de actualizar objetos Author.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Author a actualizar|Sí|Integer
body|body|Objeto Author nuevo|Sí|[Representación Detail](#recurso-author)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Author actualizado|[Representación Detail](#recurso-author)
409|Un objeto relacionado no existe|Mensaje de error
500|No se pudo actualizar el objeto Author|Mensaje de error

#### DELETE /authors/{id}

Elimina un objeto Author.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Author a eliminar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error

#### GET authors/{authorsid}/prize

Retorna una colección de objetos Prize asociados a un objeto Author en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Author a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de objetos Prize en [representación Detail](#recurso-prize)
500|Error consultando prize |Mensaje de error

#### GET authors/{authorsid}/prize/{prizeid}

Retorna un objeto Prize asociados a un objeto Author en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
authorsid|Path|ID del objeto Author a consultar|Sí|Integer
prizeid|Path|ID del objeto Prize a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Prize en [representación Detail](#recurso-prize)
404|No existe un objeto Prize con el ID solicitado asociado al objeto Author indicado |Mensaje de error
500|Error interno|Mensaje de error

#### POST authors/{authorsid}/prize/{prizeid}

Asocia un objeto Prize a un objeto Author.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
authorsid|PathParam|ID del objeto Author al cual se asociará el objeto Prize|Sí|Integer
prizeid|PathParam|ID del objeto Prize a asociar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|Objeto Prize asociado|[Representación Detail de Prize](#recurso-prize)
500|No se pudo asociar el objeto Prize|Mensaje de error

#### PUT authors/{authorsid}/prize

Es el encargado de remplazar la colección de objetos Prize asociada a un objeto Author.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
authorsid|Path|ID del objeto Author cuya colección será remplazada|Sí|Integer
body|body|Colección de objetos Prize|Sí|[Representación Detail](#recurso-prize)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|Se remplazó la colección|Colección de objetos Prize en [Representación Detail](#recurso-prize)
500|No se pudo remplazar la colección|Mensaje de error

#### DELETE authors/{authorsid}/prize/{prizeid}

Remueve un objeto Prize de la colección en un objeto Author.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
authorsid|Path|ID del objeto Author asociado al objeto Prize|Sí|Integer
prizeid|Path|ID del objeto Prize a remover|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto removido|N/A
500|Error interno|Mensaje de error



[Volver arriba](#tabla-de-contenidos)
### Recurso Book
El objeto Book tiene 2 representaciones JSON:	

#### Representación Minimum
```javascript
{
    id: '' /*Tipo Long*/,
    nombre: '' /*Tipo String*/,
    foto: '' /*Tipo String*/
}
```




#### GET /books

Retorna una colección de objetos Book en representación Detail.

#### Parámetros

#### N/A

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detail](#recurso-book)
409|Un objeto relacionado no existe|Mensaje de error
500|Error interno|Mensaje de error

#### GET /books/{id}

Retorna una colección de objetos Book en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Book a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Book en [representaciones Detail](#recurso-book)
404|No existe un objeto Book con el ID solicitado|Mensaje de error
500|Error interno|Mensaje de error

#### POST /books

Es el encargado de crear objetos Book.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|Objeto Book que será creado|Sí|[Representación Detail](#recurso-book)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Book ha sido creado|[Representación Detail](#recurso-book)
409|Un objeto relacionado no existe|Mensaje de error
500|No se pudo crear el objeto Book|Mensaje de error

#### PUT /books/{id}

Es el encargado de actualizar objetos Book.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Book a actualizar|Sí|Integer
body|body|Objeto Book nuevo|Sí|[Representación Detail](#recurso-book)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Book actualizado|[Representación Detail](#recurso-book)
409|Un objeto relacionado no existe|Mensaje de error
500|No se pudo actualizar el objeto Book|Mensaje de error

#### DELETE /books/{id}

Elimina un objeto Book.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Book a eliminar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error


#### GET books/{booksid}/review

Retorna una colección de objetos Review asociados a un objeto Book en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Book a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de objetos Review en [representación Detail](#recurso-review)
500|Error consultando review |Mensaje de error

#### GET books/{booksid}/review/{reviewid}

Retorna un objeto Review asociados a un objeto Book en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
booksid|Path|ID del objeto Book a consultar|Sí|Integer
reviewid|Path|ID del objeto Review a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Review en [representación Detail](#recurso-review)
404|No existe un objeto Review con el ID solicitado asociado al objeto Book indicado |Mensaje de error
500|Error interno|Mensaje de error

#### POST books/{booksid}/review/{reviewid}

Asocia un objeto Review a un objeto Book.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
booksid|PathParam|ID del objeto Book al cual se asociará el objeto Review|Sí|Integer
reviewid|PathParam|ID del objeto Review a asociar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|Objeto Review asociado|[Representación Detail de Review](#recurso-review)
500|No se pudo asociar el objeto Review|Mensaje de error

#### PUT books/{booksid}/review

Es el encargado de actualizar un objeto Review asociada a un objeto Book.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
booksid|Path|ID del objeto Book cuya colección será remplazada|Sí|Integer
body|body|Colección de objetos Review|Sí|[Representación Detail](#recurso-review)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|Se actualizo el objeto|Objeto Review en [Representación Detail](#recurso-review)
500|No se pudo actualizar|Mensaje de error

#### DELETE books/{booksid}/review/{reviewid}

Remueve un objeto Review asociado a un objeto Book.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
booksid|Path|ID del objeto Book asociado al objeto Review|Sí|Integer
reviewid|Path|ID del objeto Review a remover|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto removido|N/A
500|Error interno|Mensaje de error


[Volver arriba](#tabla-de-contenidos)
### Recurso Prize
El objeto Prize tiene 2 representaciones JSON:	

#### Representación Minimum
```javascript
{
    id: '' /*Tipo Long*/,
    nombre: '' /*Tipo String*/,
    descripcion: '' /*Tipo String*/
}
```

#### Representación Detail
```javascript
{
    // todo lo de la representación Minimum más los objetos Minimum con relación simple.
    author: {
    id: '' /*Tipo Long*/,
    nombre: '' /*Tipo String*/,
    descripcion: '' /*Tipo String*/    }
}
```



#### GET /prizes

Retorna una colección de objetos Prize en representación Detail.
Cada Prize en la colección tiene embebidos los siguientes objetos: Author.

#### Parámetros

#### N/A

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detail](#recurso-prize)
409|Un objeto relacionado no existe|Mensaje de error
500|Error interno|Mensaje de error

#### GET /prizes/{id}

Retorna una colección de objetos Prize en representación Detail.
Cada Prize en la colección tiene los siguientes objetos: Author.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Prize a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Prize en [representaciones Detail](#recurso-prize)
404|No existe un objeto Prize con el ID solicitado|Mensaje de error
500|Error interno|Mensaje de error

#### POST /prizes

Es el encargado de crear objetos Prize.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|Objeto Prize que será creado|Sí|[Representación Detail](#recurso-prize)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Prize ha sido creado|[Representación Detail](#recurso-prize)
409|Un objeto relacionado no existe|Mensaje de error
500|No se pudo crear el objeto Prize|Mensaje de error

#### PUT /prizes/{id}

Es el encargado de actualizar objetos Prize.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Prize a actualizar|Sí|Integer
body|body|Objeto Prize nuevo|Sí|[Representación Detail](#recurso-prize)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Prize actualizado|[Representación Detail](#recurso-prize)
409|Un objeto relacionado no existe|Mensaje de error
500|No se pudo actualizar el objeto Prize|Mensaje de error

#### DELETE /prizes/{id}

Elimina un objeto Prize.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Prize a eliminar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error



[Volver arriba](#tabla-de-contenidos)
