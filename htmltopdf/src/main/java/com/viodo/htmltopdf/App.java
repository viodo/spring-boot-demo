package com.viodo.htmltopdf;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Document;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class App {

    public static void main(String[] args) throws IOException {
        File inputHtml = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "index.html");
        Document document = Jsoup.parse(inputHtml, StandardCharsets.UTF_8.name());
        document.outputSettings().syntax(Document.OutputSettings.Syntax.html);
        // css img path
        String baseUri = ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX + "").toString();
        try (OutputStream os = Files.newOutputStream(Paths.get("E:\\Code\\Java\\spring-boot-demo\\htmltopdf\\src\\main\\resources\\index.pdf"))) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withW3cDocument(new W3CDom().fromJsoup(document), baseUri);
            builder.toStream(os);
            builder.run();
        }
    }
}
