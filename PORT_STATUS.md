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
- refined_soulstone → processed_soulstone, nombre del material en Malum 1.6.7.
- wind_charge → phantom_membrane, ingrediente de vuelo disponible en Minecraft 1.20.1.
Se mantienen las cantidades. Estas sustituciones necesitan revisión jugando.

## Estado verificado — 11 de octubre de 2026
- El fallo anterior en AgroconicWings era una llamada a getFallFlyingAttribute(), inexistente en Caelus 1.20.1. Se corrigió a getFlightAttribute().
- El commit 6438bf9157fe23d640e84b64221836c8d82aac50 completó gradlew build y generó un JAR de desarrollo.
- Ejecución: https://github.com/XaviSalvatella/Malum-Enigmatic-Arcana/actions/runs/38087937687
- La prueba runServer NO acredita un arranque correcto: registró una dependencia Malum incompatible y terminó en el aviso EULA. El código de salida cero de Gradle ocultó el problema.
- Error exacto: mods.toml exige Malum [1.6.7,1.7), pero el archivo instalado declara 1.20.1-1.6.7.
- No hay una versión jugable validada. No usar el estado verde de esa ejecución como prueba de funcionamiento.

## Trabajo por tandas
Los flujos del port pasan a ejecución manual: se eliminan push y pull_request en la rama del port, y el disparador push del ayudante forge-port.yml de main. No lanzar ambos flujos para la misma tanda.
Esta tanda solo revisó registros y configuración; no inició compilaciones ni corrigió el código del mod.

## Siguiente tanda acotada
1. Corregir el rango de Malum en src/main/resources/META-INF/mods.toml según la versión real 1.20.1-1.6.7.
2. Evitar que la comprobación de servidor se marque como validada cuando termina antes de cargar el juego; revisar registros además del código de salida.
3. Ejecutar como máximo una comprobación y guardar su resultado antes de continuar.

## Pendientes posteriores
- Convertir los loaders de modelos de neoforge: a sus equivalentes Forge, incluyendo neoforge_data.
- Revisar recetas opcionales, etiquetas Forge, atributo de crítico y tooltip de Nihility.
- Antes de reanudar, comprobar si quedan cambios locales sin subir de la sesión anterior; no suponer que están en GitHub ni sobrescribirlos.
- Verificar recetas en mundo, cliente, modelos y animaciones, vuelo, magia, daño y multijugador.
- Los cambios de balance de ingredientes siguen siendo provisionales.

No fusionar con main ni publicar como versión estable antes de esas pruebas.
Conservar la licencia del origen y sus restricciones sobre los assets.
