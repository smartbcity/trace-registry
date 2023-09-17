package city.smartb.registry.program.api.commons.utils

import city.smartb.fs.s2.file.client.FileClient
import city.smartb.fs.s2.file.domain.features.query.FileDownloadQuery
import city.smartb.fs.s2.file.domain.model.FilePathDTO
import io.ktor.utils.io.jvm.javaio.toInputStream
import java.io.InputStream
import java.net.URLConnection
import org.springframework.core.io.InputStreamResource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

suspend fun serveFile(
    fileClient: FileClient,
    getFilePath: suspend () -> FilePathDTO?
): ResponseEntity<InputStreamResource> {
    val path = getFilePath()
        ?: return buildResponseForFile("", null)

    val fileStream = FileDownloadQuery(
        objectType = path.objectType,
        objectId = path.objectId,
        directory = path.directory,
        name = path.name
    ).let { fileClient.fileDownload(it).toInputStream() }

    return buildResponseForFile(path.name, fileStream)
}

fun buildResponseForFile(filename: String, fileStream: InputStream?): ResponseEntity<InputStreamResource> {
    return ResponseEntity.ok()
        .headersForFile(filename)
        .body(InputStreamResource(fileStream ?: InputStream.nullInputStream()))
}

fun ResponseEntity.BodyBuilder.headersForFile(name: String) = apply {
    header(HttpHeaders.CONTENT_TYPE, contentTypeOfFile(name).toString())
}
private fun contentTypeOfFile(name: String): MediaType {
    return URLConnection.guessContentTypeFromName(name)
        ?.split("/")
        ?.takeIf { it.size == 2 }
        ?.let { (type, subtype) -> MediaType(type, subtype) }
        ?: MediaType.APPLICATION_OCTET_STREAM
}
