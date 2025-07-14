package org.jboss.mequal.service.utils;

import java.nio.file.Path;

import org.eclipse.microprofile.config.inject.ConfigProperty;

public class ManifestFileUtil {
    
    @ConfigProperty(name = "mequal.storage.manifest")
    String manifestsPath; 

    @ConfigProperty(name = "mequal.storage.tmp")
    String temporaryPath; 

    private String sha256;
    private Path gzippedFilePath;
    
    /*
     * We don't want to parse the file into json/xml as it could be partial or invalid
     * grepping it would be better
     */
    public static String specification(String fileContents)
    {
        return null;
    }

    public static Long compressedByteSize()
    {
        return null; 
    }

    public static String sha256()
    {
        return null;
    }


}
