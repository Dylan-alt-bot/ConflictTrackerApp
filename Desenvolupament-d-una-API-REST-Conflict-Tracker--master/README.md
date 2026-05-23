# Conflict Tracker – App FullStack

## Enllaç a la web en producció

Frontend desplegat:

```
https://conflicttracker-frontend.vercel.app
```

Backend API:

```
https://conflicttrackerapp-production.up.railway.app/api/v1
```

---

## Descripció del projecte

**Conflict Tracker** és un sistema d’informació per gestionar i visualitzar conflictes bèl·lics a nivell mundial. Permet gestionar:

* Conflictes
* Països implicats
* Faccions
* Esdeveniments associats

El sistema està dividit en:

* Backend: **Java 17 + Spring Boot 3**
* Frontend: **Vue 3 + Vite**

---

## Arquitectura del sistema

El projecte segueix una arquitectura **REST desacoblada basada en capes**:

```
Frontend (Vue 3)
      │
      ▼
REST API (Spring Boot)
      │
      ▼
Service Layer (lògica de negoci)
      │
      ▼
Repository Layer (Spring Data JPA)
      │
      ▼
PostgreSQL (Supabase)
```

### Backend (Spring Boot)

* Controllers → exposen API REST
* Services → lògica de negoci
* Repositories → accés a dades
* Entities → model JPA
* DTOs → comunicació frontend-backend
* Mappers → conversió Entity ↔ DTO

### Base de dades

* PostgreSQL (Supabase)
* Relacions:

  * ManyToMany (Conflict ↔ Country)
  * ManyToOne (Faction → Conflict)
  * ManyToMany (Faction ↔ Country)
  * OneToMany (Conflict → Event)

### Frontend (Vue 3)

* Consum d’API amb Axios
* Components modulars
* Vista de conflictes, faccions i esdeveniments
* Gestió d’estats per dades dinàmiques

---

## Variables d’entorn (deploy nou)

### Backend (Railway / qualsevol hosting)

Variables necessàries:

```
DB_URL=jdbc:postgresql://aws-0-eu-central-1.pooler.supabase.com:6543/postgres?sslmode=require
DB_USERNAME=postgres
DB_PASSWORD=TU_CONTRASENA_DE_SUPABASE
PORT=8080
```

### Important

* `DB_URL` ha d’utilitzar Supabase Transaction Pooler (recomanat per producció a Railway) 
* `sslmode=require` és obligatori per connexions externes segures
* Username sempre és `postgres` (Supabase)
* Password és el de Supabase Connect (Boto verd a la part superior)
* El port 6543 correspon al pooler de transaccions

---

### Frontend (Vercel / Netlify / local build)

Crear `.env`:

```
VITE_API_URL=https://conflicttrackerapp-production.up.railway.app/api/v1
```

---

## Execució local

### Backend (Spring Boot)

```
./mvnw spring-boot:run
```

Servidor:

```
http://localhost:8080
```

---

### Frontend (Vue 3)

```
npm install
npm run dev
```

Frontend:

```
http://localhost:5173
```

---

## API REST

URL BASE:

```
/api/v1
```

---

### Conflicts

```
GET    /conflicts
GET    /conflicts/{id}
POST   /conflicts
PUT    /conflicts/{id}
DELETE /conflicts/{id}
GET    /conflicts?status=ACTIVE
```

---

### Countries

```
GET /countries/{code}/conflicts
```

---

### Factions

```
GET    /factions
GET    /factions/{id}
GET    /factions/conflict/{conflictId}
POST   /factions
PUT    /factions/{id}
DELETE /factions/{id}
```

---

### Events

```
GET    /events
GET    /events/{id}
GET    /events/conflict/{conflictId}
POST   /events
PUT    /events/{id}
DELETE /events/{id}
```

---

## Exemples cURL

### Crear conflicte

```
curl -X POST https://conflicttrackerapp-production.up.railway.app/api/v1/conflicts \
-H "Content-Type: application/json" \
-d '{
  "name": "Segona Guerra Mundial",
  "startDate": "1939-09-01",
  "status": "ENDED",
  "description": "Conflicte global"
}'
```

---

### Obtenir conflictes

```
curl -X GET https://conflicttrackerapp-production.up.railway.app/api/v1/conflicts
```

---

## Frontend (Vue 3)

Funcionalitats:

* Llistat de conflictes
* Cercador en temps real
* Estat visual (ACTIVE / FROZEN / ENDED) amb colors
* Vista detallada de conflicte
* Països amb banderes automàtiques (FlagCDN)
* Faccions per conflicte
* Esdeveniments associats

---

## Icons de banderes

S’utilitza:

```
https://flagcdn.com
```

Basat en codis ISO2 (ex: UA, US, FR, DE)

---

## Modificacions importants del projecte

### Problemes inicials (Backend Spring Boot)

#### 1. Error JDBC / Dialect (Railway)

**Error:**

```
Unable to determine Dialect without JDBC metadata
```

**Solució:**

* Configuració correcta de `DB_URL`
* Afegit `sslmode=require`
* Variables d’entorn correctes a Railway

---

#### 2. Error connexió PostgreSQL (Network unreachable)

**Error:**

```
java.net.SocketException: Network is unreachable
```

**Solució:**

* Canvi de variable incorrecta a:

```
jdbc:postgresql://...supabase.co:5432/postgres?sslmode=require
```

---

#### 3. Relacions ManyToMany no persistien

**Problema:**

* `countries` arribaven buits al crear conflictes

**Solució:**

* Assignació manual d’entitats via `CountryRepository`
* Conversió DTO → Entity corregida

---

#### 4. Factions / Events retornaven 500

**Problema:**

* IDs incorrectes o nulls
* mapping incomplet de relacions

**Solució:**

* Validació amb `findById().orElseThrow()`
* Assignació explícita de `Conflict` i `Country`

---

### Problemes Frontend Vue

#### 1. Countries no es mostraven

**Problema:**

* Backend retornava DTO buit

**Solució:**

* Fix de mapping + càrrega de relacions JPA

