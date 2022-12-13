val detekt by configurations.creating
val detektTask = tasks.register<JavaExec>("detekt") {
    main = "io.gitlab.arturbosch.detekt.cli.Main"
    classpath = detekt
    val input = projectDir
    val config = "$projectDir/config/detekt-config.yml"
    val exclude = ".*/build/.*,.*/resources/.*"
    val params = listOf("-i", input, "-c", config, "-ex", exclude)

    args(params)
}

dependencies {
    detekt(Libraries.Detekt.core)
}