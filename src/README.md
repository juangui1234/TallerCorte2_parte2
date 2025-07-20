# 🏨 Sistema de Gestión EcoHotel Rural

Proyecto desarrollado en Java con Swing para la gestión de huéspedes, habitaciones y reservas en un EcoHotel Rural. Este sistema permite registrar, buscar y visualizar información esencial desde una interfaz gráfica amigable y funcional.

## 📌 Características principales

- Gestión de **Huéspedes** (Registro, búsqueda y eliminación)
- Gestión de **Habitaciones** (Registro, búsqueda y eliminación)
- Gestión de **Reservas** con validaciones de fechas y disponibilidad
- Interfaz gráfica mediante `JFrame`, `JDesktopPane`, `JInternalFrame` y `JTable`
- Validaciones personalizadas con excepciones
- Arquitectura basada en controladores, DTOs, DAOs y modelos

## 🧩 Estructura del Proyecto

src/
│
├── vista/
│ ├── MenuPrincipal.java # Ventana principal con barra de menú y navegación
│ ├── PanelHuesped.java # Panel para gestión de huéspedes
│ ├── PanelHabitacion.java # Panel para gestión de habitaciones
│ └── PanelReserva.java # Panel para gestión de reservas
│
├── controlador/
│ ├── HuespedControlador.java
│ ├── HabitacionControlador.java
│ └── ReservaControlador.java
│
├── dao/
│ ├── HuespedDAO.java
│ ├── HabitacionDAO.java
│ └── ReservaDAO.java
│
├── dto/
│ ├── HuespedDTO.java
│ ├── HabitacionDTO.java
│ └── ReservaDTO.java
│
├── modelo/
│ ├── Huesped.java
│ ├── Habitacion.java
│ └── Reserva.java
│
├── excepciones/
│ ├── DatoInvalidoException.java
│ ├── FechaInvalidaException.java
│ └── HabitacionNoDisponibleException.java
│
└── util/
└── IDGenerator.java # Generador de IDs para reservas

yaml
Copiar
Editar

## ✅ Requisitos Técnicos

- Java 8 o superior
- IDE como IntelliJ IDEA o NetBeans
- Proyecto tipo Java Desktop con Swing

## 📋 Validaciones implementadas

- El documento del huésped debe estar registrado para reservar
- La fecha de entrada no puede ser anterior a hoy
- La fecha de salida debe ser posterior a la fecha de entrada
- La habitación no puede estar ocupada en el rango de fechas elegido

## 👨‍💻 Autor

**Juan Guillermo Salazar**  
Proyecto desarrollado como entrega del curso de **Lenguaje de Programación**.

---

¡Gracias por usar este sistema y por promover prácticas sostenibles en el turismo! 🌱
