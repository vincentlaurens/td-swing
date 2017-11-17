package com.fredericboisguerin.insa.calculateurprix;

import static java.awt.BorderLayout.EAST;
import static java.awt.BorderLayout.SOUTH;
import static java.awt.BorderLayout.WEST;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.*;
import java.text.NumberFormat;

import javax.swing.*;

public class CalculateurPrixView extends JFrame {

    private final CalculateurPrixPresenter presenter;

    private JLabel prixArticleLabel = new JLabel("Prix d'un article (€) : ");
    private JTextField prixArticleTextField = new JTextField(10);

    private JLabel nombreArticleLabel = new JLabel("Quantité : ");
    private JTextField nombreArticleTextField = new JTextField(10);

    private JLabel montantHTLabel = new JLabel("Montant HT : ");
    private JFormattedTextField montantHTTextField = new JFormattedTextField(NumberFormat.getCurrencyInstance());

    private JLabel montantTTCLabel = new JLabel("Montant TTC : ");
    private JFormattedTextField montantTTCTextField = new JFormattedTextField(NumberFormat.getCurrencyInstance());

    private JLabel paysLabel = new JLabel("Pays");
    private JComboBox<String> paysCombo;

    private JButton computeButton = new JButton("Calculer");

    private JPanel contentPane = new JPanel();

    private JPanel labelEtFieldPane = new JPanel();
    private GridLayout labelEtFieldPaneGridLayout = new GridLayout(5, 2);





    public CalculateurPrixView() throws HeadlessException {
        super("Calculateur de prix");
        this.presenter = new CalculateurPrixPresenter(this);

        prixArticleLabel.setLabelFor(prixArticleTextField);
        prixArticleTextField.setToolTipText("Entrez le montant d'un article");


        nombreArticleLabel.setLabelFor(nombreArticleTextField);
        nombreArticleTextField.setToolTipText("Entrez la quantité d'article voulue ");


        String[] pays = {"Belgique","Danemark","Allemagne","Grèce","Espagne","France","Irlande","Italie","Chypre","Luxenbourg","Pays_Bas","Autriche","Portugal","Suède"};
        paysLabel.setLabelFor(paysCombo);
        paysCombo = new JComboBox<>(pays);
        paysCombo.setToolTipText("Sélectionnez ici le pays de votre achat");
        paysCombo.setSelectedIndex(5);


        montantHTTextField.setValue(0);
        montantHTTextField.setEditable(false);
        montantHTLabel.setLabelFor(montantHTTextField);

        montantTTCTextField.setValue(0);
        montantTTCTextField.setEditable(false);
        montantTTCLabel.setLabelFor(montantTTCTextField);





        computeButton.addActionListener(e -> this.presenter.onComputeButtonClicked(prixArticleTextField.getText(), nombreArticleTextField.getText(), paysCombo.getItemAt(paysCombo.getSelectedIndex())));

        setContentPane(contentPane);

        labelEtFieldPane.setLayout(labelEtFieldPaneGridLayout);

        labelEtFieldPane.add(prixArticleLabel);
        labelEtFieldPane.add(prixArticleTextField);

        labelEtFieldPane.add(nombreArticleLabel);
        labelEtFieldPane.add(nombreArticleTextField);

        labelEtFieldPane.add(paysLabel);
        labelEtFieldPane.add(paysCombo);

        labelEtFieldPane.add(montantHTLabel);
        labelEtFieldPane.add(montantHTTextField);

        labelEtFieldPane.add(montantTTCLabel);
        labelEtFieldPane.add(montantTTCTextField);


        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(labelEtFieldPane, WEST);
        add(computeButton, SOUTH);

        prixArticleTextField.requestFocus();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void afficherErreur(String message) {
        showMessageDialog(this, message, "Erreur", ERROR_MESSAGE);
    }


    public void afficherMontantHorsTaxe(float leMontantHTAsFloat){

        montantHTTextField.setValue(leMontantHTAsFloat);

    }

    public void afficherMontantTouteTaxeComprise(float leMontantTTCAsFloat){

        montantTTCTextField.setValue(leMontantTTCAsFloat);

    }


    public void display() {
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}