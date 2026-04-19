# 📚 Documentación de Endpoints de la API

**Base URL:** `TokenStorage.baseUrl`

---

## 📋 Pantalla "Secuencias Por Hacer" (`SecuenciasHacerView` en `main_view.dart`)

### Obtener Secuencias Pendientes
- **Servicio:** RoutinesService
- **Método:** `GET`
- **Endpoint:** `/api/v1/student/routines`
- **URL Completa:** `{baseUrl}/api/v1/student/routines`
- **Requiere:** Token de autenticación
- **Descripción:** Obtiene las secuencias de rutina pendientes asignadas al estudiante

#### Request
```
Sin body
```

#### Response (200 - OK)
```json
{
  "content": [
    {
      "id": 1,
      "name": "Rutina Mañana",
      "startTime": "08:00:00",
      "frontImageId": 123,
      "routineSequenceId": 456
    },
    {
      "id": 2,
      "name": "Rutina Tarde",
      "startTime": "14:30:00",
      "frontImageId": 124,
      "routineSequenceId": 457
    }
  ],
  "page": {
    "size": 20,
    "number": 0,
    "totalElements": 2,
    "totalPages": 1
  }
}
```

---

## 📚 Pantalla "Biblioteca de Secuencias" (`SequencesLibrary` en `sequences_library_view.dart`)

### 1. Obtener Categorías
- **Servicio:** CategoryService
- **Método:** `GET`
- **Endpoint:** `/api/v1/student/categories`
- **URL Completa:** `{baseUrl}/api/v1/student/categories`
- **Requiere:** Token de autenticación
- **Descripción:** Obtiene todas las categorías disponibles de secuencias

#### Request
```
Sin body
```

#### Response (200 - OK)
```json
{
  "content": [
    {
      "id": 1,
      "name": "Matemáticas",
      "sequenceCount": 5
    },
    {
      "id": 2,
      "name": "Lenguaje",
      "sequenceCount": 8
    },
    {
      "id": 3,
      "name": "Ciencias",
      "sequenceCount": 12
    }
  ],
  "page": {
    "size": 20,
    "number": 0,
    "totalElements": 3,
    "totalPages": 1
  }
}
```

---

### 2. Obtener Secuencias
- **Servicio:** SequenceService
- **Método:** `GET`
- **Endpoint:** `/api/v1/student/sequences`
- **URL Completa:** `{baseUrl}/api/v1/student/sequences?categoryId={categoryId}&search={searchQuery}&page={page}`
- **Requiere:** Token de autenticación
- **Descripción:** Obtiene secuencias con filtros opcionales de categoría, búsqueda y paginación

#### Query Parameters
| Parámetro | Tipo | Descripción | Requerido |
|-----------|------|-------------|-----------|
| `categoryId` | string | ID de la categoría para filtrar | No |
| `search` | string | Término de búsqueda por título | No |
| `page` | int | Número de página (default: 0) | No |

#### Request
```
Sin body
```

#### Response (200 - OK)
```json
{
  "content": [
    {
      "id": 1,
      "title": "Aprender números",
      "description": "Secuencia para aprender los números del 1 al 10",
      "category": "Matemáticas",
      "steps": 5
    },
    {
      "id": 2,
      "title": "Contar hasta 20",
      "description": "Actividad de conteo de números",
      "category": "Matemáticas",
      "steps": 8
    }
  ],
  "page": {
    "size": 20,
    "number": 0,
    "totalElements": 15,
    "totalPages": 1
  }
}
```

---

## 🎬 Pantalla "Reproducir Secuencia" (`PlaySequenceView` → `PlaySequencePage`)

### 1. Obtener Detalles de una Secuencia
- **Servicio:** SequenceService
- **Método:** `GET`
- **Endpoint:** `/api/v1/student/sequences/{sequenceId}`
- **URL Completa:** `{baseUrl}/api/v1/student/sequences/{sequenceId}`
- **Requiere:** Token de autenticación
- **Parámetros URL:** `sequenceId` (int - ID de la secuencia)
- **Descripción:** Obtiene los detalles completos de una secuencia incluyendo todos sus pasos

#### Request
```
Sin body
```

#### Response (200 - OK)
```json
{
  "id": 1,
  "title": "Aprender números",
  "description": "Secuencia para aprender los números del 1 al 10",
  "category": "Matemáticas",
  "steps": [
    {
      "id": 1,
      "name": "Paso 1: Ver el número 1",
      "position": 0,
      "arasaacPictogramId": 100
    },
    {
      "id": 2,
      "name": "Paso 2: Señalar el número 1",
      "position": 1,
      "arasaacPictogramId": 101
    },
    {
      "id": 3,
      "name": "Paso 3: Decir el número 1",
      "position": 2,
      "arasaacPictogramId": 102
    }
  ]
}
```

---

### 2. Iniciar Reproducción
- **Servicio:** ReproductionService
- **Método:** `POST`
- **Endpoint:** `/api/v1/student/reproductions/{routineSequenceId}`
- **URL Completa:** `{baseUrl}/api/v1/student/reproductions/{routineSequenceId}`
- **Requiere:** Token de autenticación
- **Parámetros URL:** `routineSequenceId` (int - ID de la secuencia de rutina)
- **Descripción:** Inicia una nueva reproducción de una secuencia y retorna el ID de la reproducción

#### Request
```json
{}
```

#### Response (201 - Created)
```
reproductionId (int - por ejemplo: 789)
```

---

### 3. Finalizar Reproducción
- **Servicio:** ReproductionService
- **Método:** `PUT`
- **Endpoint:** `/api/v1/student/reproductions/{reproductionId}/end`
- **URL Completa:** `{baseUrl}/api/v1/student/reproductions/{reproductionId}/end`
- **Requiere:** Token de autenticación
- **Parámetros URL:** `reproductionId` (int - ID retornado al iniciar la reproducción)
- **Descripción:** Marca la reproducción como completada

#### Request
```json
{}
```

#### Response (200 - OK)
```
Respuesta vacía
```

---

## 👤 Pantalla "Perfil" (`ProfileView`)

### 1. Obtener Perfil del Estudiante
- **Servicio:** UserService
- **Método:** `GET`
- **Endpoint:** `/api/v1/student/user/profile`
- **URL Completa:** `{baseUrl}/api/v1/student/user/profile`
- **Requiere:** Token de autenticación
- **Descripción:** Obtiene los datos personales del estudiante autenticado

#### Request
```
Sin body
```

#### Response (200 - OK)
```json
{
  "id": 100,
  "username": "juan_lopez",
  "email": "juan@example.com",
  "name": "Juan",
  "lastName": "López"
}
```

---

### 2. Obtener Información del Profesor
- **Servicio:** UserService
- **Método:** `GET`
- **Endpoint:** `/api/v1/student/user/teacher`
- **URL Completa:** `{baseUrl}/api/v1/student/user/teacher`
- **Requiere:** Token de autenticación
- **Descripción:** Obtiene la información del profesor asignado al estudiante

#### Request
```
Sin body
```

#### Response (200 - OK)
```json
{
  "id": 1,
  "username": "maria_garcia",
  "email": "maria@example.com",
  "name": "María",
  "lastName": "García"
}
```

---

## 📊 Resumen de Endpoints por Pantalla

### Secuencias Por Hacer
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/v1/student/routines` | Obtener secuencias pendientes |

### Biblioteca de Secuencias
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/v1/student/categories` | Obtener categorías |
| GET | `/api/v1/student/sequences` | Obtener secuencias (con filtros) |

### Reproducir Secuencia
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/v1/student/sequences/{id}` | Obtener detalles de secuencia |
| POST | `/api/v1/student/reproductions/{id}` | Iniciar reproducción |
| PUT | `/api/v1/student/reproductions/{id}/end` | Finalizar reproducción |

### Perfil
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/v1/student/user/profile` | Obtener perfil del estudiante |
| GET | `/api/v1/student/user/teacher` | Obtener información del profesor |

---