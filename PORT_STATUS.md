# Port de Enigmatic Arcana a Forge 1.20.1 — trabajo incompleto

Fecha: 10-10-2026. NO es una versión jugable y NO hay un JAR validado.

Origen: https://github.com/Hazentouvel/Malum-Enigmatic-Arcana
Commit de partida: a4264acc0161d0aa8f8b10bef6e2d468aec421ce
Rama local: port/forge-1.20.1

## Cambios iniciales
- Base de construcción ForgeGradle 6, Forge 47.4.0, Java 17 y Gradle 8.8.
- Dependencias 1.20.1 iniciales seleccionadas; resolución aún sin validar.
- Registro de objetos: DeferredItem/DeferredHolder -> RegistryObject.
- Configuración y registro de pestaña creativa adaptados a Forge.
- Metadatos mods.toml para Forge con aviso explícito de versión incompleta.

## Resultado real de la prueba
Se ejecutó `gradle compileJava --no-daemon` con Gradle 8.8 y Java 17.
Falló ANTES de compilar Java: no pudo resolver ForgeGradle.
La ejecución con --info confirma `java.net.SocketException: Network is unreachable`
en las peticiones a plugins.gradle.org y maven.minecraftforge.net.
No hay evidencia de compilación correcta ni pruebas dentro del juego.
Aunque se resuelva la conexión, las clases pendientes aún impedirán compilar.

## Adaptaciones pendientes
- Entrada del mod, eventos y registro de renderizados de cliente.
- Reemplazar las clases de HazentouveLib (alas, renderizadores, armadura).
- Armaduras: Holder<ArmorMaterial> -> ArmorMaterial; componentes -> NBT.
- Atributos: Holder/ResourceLocation -> Attribute/UUID; operaciones antiguas.
- Rarezas: EnumProxy de NeoForge -> extensiones de Forge.
- Antonomos: constructor antiguo de SpellBook, atributos mediante Curios.
- Nihility: interfaz antigua de Malum y contenedor de hechizos de Iron's.
- Catastrophe: disparo de arco, consumo de munición y renderizado de 1.20.1.
- GeckoLib: GeoRenderProvider -> IClientItemExtensions/initializeClient.
- Recetas, tags, reparación espiritual e integraciones opcionales.
- Compilar, probar cliente y servidor, verificar multijugador y modpack.

## API de Malum verificada en su rama 1.20.1
`com.sammy.malum.registry.common.AttributeRegistry` contiene:
SCYTHE_PROFICIENCY, ARCANE_RESONANCE, SOUL_WARD_INTEGRITY,
SOUL_WARD_RECOVERY_RATE y SOUL_WARD_CAP.
El último corresponde a SOUL_WARD_CAPACITY en la versión nueva.
Todos son RegistryObject<Attribute> y requieren .get() al construir modificadores.
Fuente: https://github.com/SammySemicolon/Malum-Mod/tree/1.20.1

Iron's de referencia local: rama 1.20.1-legacy, versión 3.4.0.11.
Su SpellBook usa constructor (int, SpellRarity, Item.Properties), y el
contenedor de hechizos se guarda con spellContainer.save(itemStack).
La dependencia inicial 3.4.0.7 debe contrastarse o actualizarse antes de compilar.

## Continuación
Crear un fork del origen en la cuenta de trabajo. Aplicar el parche allí.
Completar el código pendiente y usar un entorno con acceso a Maven (por ejemplo,
GitHub Actions) para iterar sobre los errores de compilación. El fork no resuelve
por sí solo el código pendiente y no garantiza que el port funcione.

El parche no incluye texturas, modelos ni animaciones. Conservar la licencia del
proyecto; su archivo TEMPLATE_LICENSE.txt contiene restricciones sobre sus assets.
