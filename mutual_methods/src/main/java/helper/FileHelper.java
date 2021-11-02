package helper;

import enums.MimTypes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static enums.MimTypes.*;

public class FileHelper {

    private final Logger log = LogManager.getLogger(FileHelper.class);

    /**
     * Belirtilen dizindeki dosyayı String olarak okur.
     * Örneğin: .sql uzantılı dosyaları string olarak okumak için kullanılır
     * Örnek Kullanım: C:\deneme.sql
     *
     * @param fileDirectory : dosya yolu
     * @return string değer döner
     */
    public String readFileAsString(String fileDirectory) {
        try {
            return new String(Files.readAllBytes(Paths.get(fileDirectory)));
        } catch (IOException e) {
            log.error("An error occurred message:{}", e.getMessage());
            return "";
        }
    }


    protected String getFileMimeType(File file) {
        String type = file.getName().split("[.]")[1].toLowerCase();

        switch ( MimTypes.valueOf(type) ) {

            case XLS:
                return XLS.getText();
            case JS:
                return JS.getText();
            case JSON:
                return JSON.getText();
            case DOC:
                return DOC.getText();
            case PDF:
                return PDF.getText();
            case SQL:
                return SQL.getText();
            case XLSX:
                return XLSX.getText();
            case PPT:
                return PPT.getText();
            case ODT:
                return ODT.getText();
            case PPTX:
                return PPTX.getText();
            case DOCX:
                return DOCX.getText();
            case APNG:
                return APNG.getText();
            case AVIF:
                return AVIF.getText();
            case FLIF:
                return FLIF.getText();
            case JPEG:
                return JPEG.getText();
            case JPG:
                return JPG.getText();
            case JFIF:
                return JFIF.getText();
            case PJPEG:
                return PJPEG.getText();
            case PJP:
                return PJP.getText();
            case JXL:
                return JXL.getText();
            case PNG:
                return PNG.getText();
            case SVG:
                return SVG.getText();
            case WEBP:
                return WEBP.getText();
            case CSV:
                return CSV.getText();
            case TXT:
                return TXT.getText();
            default:
                return null;
        }
    }
}
