# Visualizador de Configuración de Ordenadores 🖥️

Este proyecto es una aplicación Java que permite visualizar detalladamente las especificaciones técnicas de ordenadores almacenadas en formato JSON.

## Características ✨

- **Lectura de archivos JSON** 📂: Carga y procesa datos de configuración de ordenadores desde archivos JSON.
- **Visualización detallada** 🔍: Muestra información completa sobre todos los componentes del ordenador.
- **Interfaz gráfica amigable** 🖼️: Presenta los datos de forma organizada y visualmente atractiva.

## Componentes mostrados 🛠️

- Información básica (nombre y precio) 💰
- Procesador(es) 🧠
- Placa(s) base 🖧
- Módulos de RAM 🧵
- Discos duros 💾
- Fuentes de alimentación ⚡
- Tarjetas gráficas 🎮
- Periféricos (teclado, ratón, monitor) 🖱️⌨️🖥️

## Tecnologías utilizadas 🛠️

- Java 21 ☕
- Swing (para la interfaz gráfica) 🖼️
- Gson (para procesamiento de JSON) 📄
- Lombok (para reducir código boilerplate) ✂️
- Maven (para gestión de dependencias) 📦

## Requisitos 📋

- Java 21 o superior ☕
- Maven 📦

## Instalación 🚀

1. Clona este repositorio:
   ```
   git clone https://github.com/AleIb12/LectorJsonOrdenador1.git
   ```

2. Navega al directorio del proyecto:
   ```
   cd JsonOrdenador
   ```

3. Compila el proyecto con Maven:
   ```
   mvn clean package
   ```

## Uso 🏃‍♂️

1. Ejecuta la aplicación:
   ```
   java -jar target/JsonOrdenador-1.0-SNAPSHOT.jar
   ```

2. La aplicación cargará automáticamente el archivo de configuración `Ordenador.json`

## Formato del archivo JSON 📄

El archivo JSON debe tener la siguiente estructura:

```json
{
  "Ordenador": {
    "nombre": "Nombre del ordenador",
    "precio": 1234,
    "procesador": [...],
    "PlacaBase": [...],
    "Ram": [...],
    "DiscoDuro": [...],
    "FuenteAlimentacion": [...],
    "TarjetaGrafica": [...],
    "Perifericos": [...]
  }
}
```

## Autora ✍️

Alisha

## Licencia 📜

Este proyecto está licenciado bajo MIT