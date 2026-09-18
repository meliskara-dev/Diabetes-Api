# Diabetes API

A backend API for a personal diabetes tracking application.

## Project Purpose

Diabetes API is being developed to provide a structured backend for tracking everyday diabetes-related records in one place.

The application will allow a single user to record information such as fasting or postprandial glucose measurements, insulin doses, and carbohydrate intake. These records will later be used to display recent values, daily totals, historical logs, and data that can be shown in charts.

The goal is to make personal tracking data easier to store, retrieve, and review over time. The backend exposes this data through HTTP endpoints so that a future mobile application can use it.

This project does not provide medical diagnoses, treatment plans, or insulin dosage recommendations. It is intended only for personal record keeping and data visualization.

## Technologies

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Docker Compose

## Run locally

1. Start PostgreSQL:

   ```bash
   docker compose up -d
   ```

## Data Model Decisions

These decisions were made before writing the first entity. Each one is listed with the reason behind it.

| Topic | Decision | Reason |
|---|---|---|
| Table structure | Single `entry` table with a `type` column | All record types share the same shape (type, value, time), so recent values and the log list can be read from one table with one query. |
| Value type | `BigDecimal`, stored as `numeric(6,2)` | `double` cannot represent some decimals exactly (`0.1 + 0.2 = 0.30000000000000004`); insulin doses and daily totals must be exact. |
| Enum mapping | `@Enumerated(EnumType.STRING)` | `ORDINAL` stores the enum position, so adding or reordering values would silently change the meaning of existing rows. |
| Time | `LocalDateTime`, stored as `timestamp` in Europe/Istanbul time | There is a single user living in Istanbul, and Türkiye no longer uses daylight saving time. Storing the local wall-clock time keeps reads, day boundaries and responses simple, with no conversion step. |
| Id strategy | `Long` with `GenerationType.IDENTITY` | Records are inserted one at a time by a single user, so the batch insert advantage of `SEQUENCE` is not needed and the simplest option is enough. |

### Trade-offs

- **Single table:** Rules that differ per type (for example valid value ranges) are not enforced by the table structure. They will be handled by the validator and database constraints.
- **`numeric(6,2)`:** The maximum value is 9999.99, which covers glucose (mg/dL), carbohydrates (g) and insulin (units).
- **`IDENTITY`:** Hibernate cannot batch inserts with this strategy. This is acceptable at this project's scale.
- **`LocalDateTime`:** The value has no time zone, so its meaning depends on the zone it was created in. `LocalDateTime.now()` uses the JVM default zone; if the server runs in UTC (for example in a container), times would be stored 3 hours off. The zone must therefore be fixed to Europe/Istanbul explicitly. If the app ever needs to support users in other zones, switching back to `Instant` would require a data migration.