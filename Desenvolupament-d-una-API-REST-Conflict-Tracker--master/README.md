# Conflict Tracker – Spring Boot + Frontend Vue 3

## Descripció del projecte

**Conflict Tracker** és un sistema d'informació. La seva finalitat és gestionar i fer un seguiment dels conflictes bèl·lics a nivell mundial, incloent-hi les faccions involucrades, els països participants i els esdeveniments clau associats a cada conflicte.

El sistema te:

* Backend: **Java 17 + Spring Boot 3**
* Frontend: **Vue 3**

L’objectiu del projecte és demostrar una arquitectura backend robusta i una interfície web consumible que treballa sobre la mateixa API REST.

---

El projecte utilitza:

* DTOs per desacoblar entitats internes
* Mappers per conversió Entity ↔ DTO
* Relacions JPA:

   * ManyToMany (Conflict ↔ Country)
   * ManyToOne (Faction → Conflict)
   * ManyToMany (Faction ↔ Country)
   * OneToMany (Conflict → Event)

---

## Classes principals

### Conflicts (Conflictes)

* name
* startDate
* status (ACTIVE, FROZEN, ENDED)
* description
* countries implicats

### Countries (Paisos)

* name
* code (ISO2 usat per banderes i icones)

### Factions (Bandols)

* name
* conflict associat
* països membres

### Events (Esdeveniments)

* eventDate
* location
* description
* conflict associat

---

## Execució del projecte

### Backend (Spring Boot)

Des d’IntelliJ: **Run Application** (dins de la carpeta backend)

Servidor backend:

```
http://localhost:8080
```

---

### Frontend (Vue 3)

Des d'Visual Studio Code (projecte Vue dins de la carpeta frontend):

```bash
npm install
npm run dev
```

Frontend:

```
http://localhost:5173
```

---

## API REST

URL base:

```
http://localhost:8080/api/v1
```

---

### Conflicts (Conflictes)

```http
GET    /conflicts
GET    /conflicts/{id}
POST   /conflicts
PUT    /conflicts/{id}
DELETE /conflicts/{id}
```

Filtres:

```
GET /conflicts?status=ACTIVE
```

---

### Countries → Conflicts (Paisos → Conflictes on participen)

```
GET /countries/{code}/conflicts
```

---

### Factions (Bandols)

```
GET    /factions
GET    /factions/{id}
GET    /factions/conflict/{conflictId}
POST   /factions
PUT    /factions/{id}
DELETE /factions/{id}
```

---

### Events (Esdeveniments)

```
GET    /events
GET    /events/{id}
GET    /events/conflict/{conflictId}
POST   /events
PUT    /events/{id}
DELETE /events/{id}
```

---

## Exemples amb cURL

### Obtenir tots els conflictes

```
curl -X GET http://localhost:8080/api/v1/conflicts
```

---

### Filtrar per estat

```
curl -X GET "http://localhost:8080/api/v1/conflicts?status=ACTIVE"
```

---

### Conflictes per país

```
curl -X GET http://localhost:8080/api/v1/countries/UA/conflicts
```

---

### Detall conflicte

```
curl -X GET http://localhost:8080/api/v1/conflicts/1
```

---

### Crear conflicte

```
curl -X POST http://localhost:8080/api/v1/conflicts \
-H "Content-Type: application/json" \
-d '{
  "name": "Segona Guerra Mundial",
  "startDate": "1939-09-01",
  "status": "ENDED",
  "description": "Conflicte global entre 1939 i 1945"
}'
```

---

## Frontend (Vue 3)

L’aplicació Vue consumeix directament l’API Spring Boot.

### Funcionalitats:

* Llistat de conflictes en format tabla
* Cercador en temps real
* Cards amb estats en color
* Vista de detall per conflicte
* Banderes automàtiques per país (FlagCDN API)
* Faccions amb països suport
* Esdeveniments per conflicte
* Botó de tornar enrere

---

## Icons Banderes 

Les banderes es carreguen automàticament des de:

```
https://flagcdn.com
```

Basat en codis ISO2 (ex: UA, US, DE, FR)

