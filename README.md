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