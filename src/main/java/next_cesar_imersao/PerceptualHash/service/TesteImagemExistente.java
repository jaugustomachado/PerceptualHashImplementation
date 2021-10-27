package next_cesar_imersao.PerceptualHash.service;

import java.io.File;

public class TesteImagemExistente {

    private static boolean existe;

    private static void listFilesForFolder(final File folder, String nomeDaImagem) {

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry,nomeDaImagem);
            } else {
                if (  nomeDaImagem.equals(fileEntry.getName())  ){
                    existe = true;
                    return;
                } else {
                    existe = false;
                }
            }
        }
    }

    public static boolean existeONome(String caminho, String nomeDaImagem) {
        final File folder = new File(caminho);
        listFilesForFolder(folder, nomeDaImagem);
        return existe;
    }
}
