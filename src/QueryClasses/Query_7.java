package src.QueryClasses;

public class Query_7 {
    private String nom;
    private Integer noteAvant;
    private String dateNoteAvant;
    private Integer noteApres;
    private String dateNoteApres;
    private String typeFormation;
    private String dateDebutFormation;
    private Integer pays;

    public Query_7(String nom, Integer noteAvant, String dateNoteAvant, Integer noteApres, String dateNoteApres, String typeFormation, String dateDebutFormation, Integer pays) {
        this.nom = nom;
        this.noteAvant = noteAvant;
        this.dateNoteAvant = dateNoteAvant;
        this.noteApres = noteApres;
        this.dateNoteApres = dateNoteApres;
        this.typeFormation = typeFormation;
        this.dateDebutFormation = dateDebutFormation;
        this.pays = pays;
    }

    public String getNom() {
        return nom;
    }

    public Integer getNoteAvant() {
        return noteAvant;
    }

    public String getDateNoteAvant() {
        return dateNoteAvant;
    }

    public Integer getNoteApres() {
        return noteApres;
    }

    public String getDateNoteApres() {
        return dateNoteApres;
    }

    public String getTypeFormation() {
        return typeFormation;
    }

    public String getDateDebutFormation() {
        return dateDebutFormation;
    }

    public Integer getPays() {
        return pays;
    }
}
