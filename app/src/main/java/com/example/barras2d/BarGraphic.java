package com.example.barras2d;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by "vários autores" on 17/05/2018.
 */

public class BarGraphic extends View {
    Integer[] values;
    Integer maior;  //maior dos valores do gráfico
    Integer marginV = 7;
    Integer fontSize = 35;

    // valores para cores:
    Integer initialR = 255;
    Integer initialG = 0;
    Integer initialB = 0;

    Integer finalR = 121;
    Integer finalG = 201;
    Integer finalB = 161;

    public BarGraphic(Context context, AttributeSet atts) {
        super(context, atts);
    }

    // aqui será calculado a maior qtde:
    public void setParams(Integer[] values){
        if(values == null)
            values = new Integer[0];

        //determinaremos a seguir o maior dos valores (a maior das qtdes. de avaliações)
        this.maior = 0; //atributo da classe para guardar o maior dos valores das barras
        //o gráfico poderia ter qualquer qtde. de barras, é uma lógica geral:
        for(int i = 0; i < values.length; i++) {
            if(values[i] > this.maior)
                this.maior = values[i];
        }

        this.values = values;

        postInvalidate(); //provoca executar o método onDraw para desenhar
    }

    // aqui vamos desenhar o gráfico de barras (com retângulos horizontais)
    @Override
    protected void onDraw(Canvas c) {
        //aqui que vamos desenhar
        if(values == null || values.length == 0)
            return;

        Paint p = new Paint(); //criamos um pincel

        Integer cHeight = c.getHeight();  //pegamos a altura da área gráfica
        Integer cWidth = c.getWidth();  //pegamos a largura da área gráfica
        Integer height = cHeight / values.length;

        Integer deltaR = (finalR - initialR) / (values.length - 1);
        Integer deltaG = (finalG - initialG) / (values.length - 1);
        Integer deltaB = (finalB - initialB) / (values.length - 1);

        // ciclo para desenhar as diferentes
        // barras ( as qtdes estão no vetor values[] );
        // foi elaborada uma lógica geral, poderiam ser menos ou mais barras
        for(int i = 0; i < values.length; i++){
            // para gerenciar as cores das barras:
            Integer r = initialR + (deltaR * i);
            Integer g = initialG + (deltaG * i);
            Integer b = initialB + (deltaB * i);

            Integer top = (cHeight - height) - (height * i + marginV);
            Integer bottom = top + height - marginV;

            // desenha (escreve) os números das estrelas (1, 2, 3, 4, 5):
            p.setColor(0xFF000000);
            p.setTextSize(fontSize);
            c.drawText( new Integer(i+1).toString(), 0,
                    top + ((bottom - top) / 2) + fontSize/2, p);

            //estabelecemos a cor do pincel p:
            p.setARGB(255, r, g, b);
            if(i==1)p.setARGB(255,0,100,255);
            // desenhamos o retângulo da barra, proporcionalmente (em escala)
            // ao maior valor (guardado no atributo 'maior'):
            c.drawRect(fontSize,   //fontSize é o ponto left
                    top,
                    (values[i] / maior.floatValue() * cWidth) + fontSize,
                    bottom,
                    p);
            //usamos este drawRect:
            //public void drawRect (float left,
            //                      float top,
            //                      float right,
            //                      float bottom,
            //                      Paint paint)
        }
    }
}
