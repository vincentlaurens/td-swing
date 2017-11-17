package com.fredericboisguerin.insa.calculateurprix;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculateurPrixPresenter {
    private final CalculateurPrixView calculateurPrixView;

    public CalculateurPrixPresenter(CalculateurPrixView calculateurPrixView) {
        this.calculateurPrixView = calculateurPrixView;
    }

    public void onComputeButtonClicked(String montantArticleAsText, String quantiteArticleAsText, String nomDuPays) {

        boolean ilExisteUneErreur = false;

        if(montantArticleAsText.isEmpty()){
            calculateurPrixView.afficherErreur("Le champ \"montant de l'article\" est obligatoire");
            ilExisteUneErreur = true;
        }

        if (montantArticleAsText.matches("[a-zA-Z]")){
            calculateurPrixView.afficherErreur("Le champ \" montant de l'article\" doit contenir une valeur numérique !");
            ilExisteUneErreur = true;
        }
        if(quantiteArticleAsText.isEmpty()){
            calculateurPrixView.afficherErreur("Le champ \"quantité de l'article\" est obligatoire");
            ilExisteUneErreur = true;
        }

        if (quantiteArticleAsText.matches("[a-zA-Z]")){
            calculateurPrixView.afficherErreur("Le champ \"quantité de l'article\" doit contenir une valeur numérique !");
            ilExisteUneErreur = true;
        }
        if(quantiteArticleAsText.matches("\\d+([,.]\\d*)")){
            calculateurPrixView.afficherErreur("Le champ \"quantité de l'article\" doit contenir une valeur numérique entière !\"");
            ilExisteUneErreur = true;

        }

        if(!ilExisteUneErreur){

            float montantArticleAsFloat = Float.parseFloat(montantArticleAsText);
            int quantiteArticleAsInt = Integer.parseInt(quantiteArticleAsText);

            Pays pays = Pays.valueOf(nomDuPays);

            double tauxDeTaxe = 0;


            switch (pays){
                case Allemagne:
                    tauxDeTaxe = 1.19;
                    break;
                case Belgique:
                    tauxDeTaxe = 1.21;
                    break;
                case Danemark:
                    tauxDeTaxe = 1.25;
                    break;
                case Grèce:
                    tauxDeTaxe = 1.23;
                    break;
                case Espagne:
                    tauxDeTaxe = 1.21;
                    break;
                case France:
                    tauxDeTaxe = 1.20;
                    break;
                case Irlande:
                    tauxDeTaxe = 1.23;
                    break;
                case Italie:
                    tauxDeTaxe = 1.22;
                    break;
                case Chypre:
                    tauxDeTaxe = 1.19;
                    break;
                case Luxenbourg:
                    tauxDeTaxe = 1.15;
                    break;
                case Pays_Bas:
                    tauxDeTaxe = 1.21;
                    break;
                case Autriche:
                    tauxDeTaxe = 1.20;
                    break;
                case Portugal:
                    tauxDeTaxe = 1.23;
                    break;
                case Suède:
                    tauxDeTaxe = 1.25;
                    break;

                default:
                    tauxDeTaxe = 1;

            }
            calculerCout(montantArticleAsFloat, quantiteArticleAsInt, tauxDeTaxe);

        }



    }

    public void calculerCout(float montantArticle, int quantiteArticle, double taxRate){

        float montantHTAsFloat = montantArticle * quantiteArticle;
        float montantTTCAsFloat = (float) (montantHTAsFloat * taxRate);


        calculateurPrixView.afficherMontantHorsTaxe(montantHTAsFloat);
        calculateurPrixView.afficherMontantTouteTaxeComprise(montantTTCAsFloat);



    }
}