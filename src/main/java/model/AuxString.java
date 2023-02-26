package model;

/**
 * Aceasta clasa este folosita pentru transmiterea "prin referinta" a unei matrice de String-uri functiei view din AbstractDAO
 *
 */
public class AuxString {
    private String[][] M;
    public AuxString(String[][] a){
        M=a;
    }

    /**
     * Transforma o matrice de String-uri intr-un sir de String-uri, unde fiecare element
     * este un String care reprezinta o linie din matricea anterioara
     * concatenata sub un anumit format. Se foloseste la popularea ComboBox-urilor pentru
     * selectia clientilor si produselor.
     * @param nrAtr numarul de atribute al obiectului pentru care aplicam metoda.
     * @return se returneaza sirul de String-uri generat
     */
    public String[] getStrings(int nrAtr){
        String[] a = new String[M.length];
        for(int i=0;i<M.length;i++){
            for(int j=0;j<nrAtr;j++){
                a[i]+=M[i][j];
                a[i]+=" ";
            }
            a[i]=a[i].substring(4); //sterg primele 3 caractere pt ca mi pune null
        }
        return a;
    }

    public String[][] getM() {
        return M;
    }

    public void setM(String[][] m) {
        M = m;
    }
}
