# Port Forge 1.20.1 — en desarrollo

Rama: `port/forge-1.20.1`. PR: https://github.com/XaviSalvatella/Malum-Enigmatic-Arcana/pull/1
Origen: Hazentouvel/Malum-Enigmatic-Arcana, commit a4264acc0161d0aa8f8b10bef6e2d468aec421ce.

## Adaptado
- Forge 47.4.0, Java 17, ForgeGradle 6 y Gradle 8.8.
- Malum 1.6.7, Iron's Spells 1.20.1-3.16.3 e Iron's Lib 2.1.0.
- Registro, eventos, rarezas, atributos y contenedores de hechizos de Forge.
- Armadura, libro Antonomos, Bulwark, alas, Nihility y arco Catastrophe.
- Clases propias sustituyen la dependencia de HazentouveLib.
- GeckoLib 4 y renderizadores de Curios 5. La posición visual necesita prueba en cliente.
- 77 recetas convertidas al formato de Malum 1.6.7; rutas recipes y tags/items.
- Los generadores NeoForge quedan excluidos: se usan los JSON convertidos.

## Sustituciones de ingredientes (balance provisional)
- soulwoven_silk → spirit_fabric, material textil disponible en Malum 1.6.7.
- paracausal_flame → fused_consciousness, material avanzado disponible en Malum 1.6.7.
- wind_charge → phantom_membrane, ingrediente de vuelo disponible en Minecraft 1.20.1.
Se mantienen las cantidades. Estas sustituciones necesitan revisión jugando.

## Validación pendiente
GitHub Actions está habilitado y compila con Java 17. Aún no se ha confirmado
una compilación correcta de esta adaptación. El entorno local no puede resolver
Maven desde Java. No hay JAR jugable validado.
Pendientes: compilar, arranque de servidor, recetas e integraciones opcionales,
prueba visual de modelos/animaciones, vuelo, magia, daño y multijugador.
Las recetas opcionales se conservan con condiciones Forge; no todos los mods
opcionales del original están disponibles en esta versión.

No fusionar con main ni publicar como versión estable antes de esas pruebas.
Conservar la licencia del origen y sus restricciones sobre los assets.
