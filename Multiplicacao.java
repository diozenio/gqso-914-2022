import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class Multiplicacao implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            String[] partes = exchange.getRequestURI().getPath().split("/");
            List<Double> numeros = new ArrayList<Double>();

            // Verificação da URL que possua somente números
            for (int i = 2; i < partes.length; i++) {
                boolean isNumeric = checkStr(partes[i]);
                if (isNumeric) {
                    double d = Double.parseDouble(partes[i]);
                    numeros.add(d);
                } else {
                    byte[] resposta = "URL inválido".getBytes();
                    exchange.sendResponseHeaders(200, resposta.length);
                    exchange.getResponseBody().write(resposta);
                    return;
                }
            }

            // Multiplicação da lista de números da URL
            double multiplicacao = 1;
            for(int cont = 0; cont<numeros.size();cont++){
                multiplicacao *= numeros.get(cont);
            }

            // Resposta válida
            byte[] resposta = Double.toString(multiplicacao).getBytes();
            exchange.sendResponseHeaders(200, resposta.length);
            exchange.getResponseBody().write(resposta);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            exchange.close();
        }
    }

    // Função que verifica se a string é um número
    private boolean checkStr(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}