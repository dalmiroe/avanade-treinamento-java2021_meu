package com.avanade.aplicacao;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.Locale;

/*
aula d8/d9
coisas static são carregados automaticamente e consome memória
mudar o nome de uma variável de um so vez - seleciona e s-f6

*/
public class Programa {
    //tipo primitivos
    long longo;
    int inteiro;
    double decimal;
    float decimal2;
    boolean boleano;
    //Declaração com objeto
    Integer inteiro2 = 10;
    //public static void main(String[] args) = abaixo
    public static void main(String...args) {

        //System.out.println("Hello World");
        if (args.length == 0){
            System.out.println("Parâmetro caminho obrigatório");
            System.out.println("Uso:");
            System.out.println("java com.avanade.aplicacao.Programa [CAMINHO]");
            System.exit(-1);
            //vai sair do sistema
        }
        System.out.println("Iniciando aplicação...");
        Programa programa = new Programa();
        programa.iniciar(args[0]);

    }
    private boolean mensagemValidacao(String caminhoDirEntrada, String mensagem){
        String modeloMensagem = mensagem + " [{0}] ";
        System.err.println(MessageFormat.format(modeloMensagem,caminhoDirEntrada));
        System.exit(-2);
        return false;
    }

    private boolean validarDirEntrada(String caminhoDirEntrada){
        System.out.println("Validação do diretório de entrada...");

        Path dirEntrada = Paths.get(caminhoDirEntrada);
        if (!Files.exists(dirEntrada)){
            return mensagemValidacao(caminhoDirEntrada,"Diretório informando não existe");
        }

        if (!Files.isDirectory(dirEntrada)){
            return mensagemValidacao(caminhoDirEntrada,"Caminho informado não é um diretório");
        }

        File fDirEntra = dirEntrada.toFile();
        String[] arquivos = fDirEntra.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toUpperCase().endsWith(".TXT");
            }
        });
        if (arquivos == null || arquivos.length ==0){
            return mensagemValidacao(caminhoDirEntrada,"Não há arquivos no diretório informando");
        }


        System.out.println("Diretório validade com sucesso " + caminhoDirEntrada);
        return true;
    }
    public void iniciar(String caminhoDirEntrada) {
        if (!validarDirEntrada(caminhoDirEntrada)){
            return;
        }
    }
}
