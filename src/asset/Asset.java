package asset;
/**
 * 
 * @author daniel und manuel
 *
 */
public abstract class Asset {
	public final String name;
    /**
     * Asset(String name).
     * @param name
     * Erstellt eine neues Asset Objekt mit dem Speizifizierten Namen
     */
    public Asset(String name) {
        this.name = name;
    }
    /**
     * String toString().
     * @return String
     * gibt " Name: "<Asset.name> zur�ck
     */
    public String toString() {
        return ("  Name: "+name);
    }
    /**
     * abstract long getvalue().
     * @return
     * Abstrakte Methode gibt den Value eines Assets (Account etc.) in Cent zur�ck
     */
    public abstract long getvalue();
    /**
     * boolean equals(Object first, Object second).
     * Vergleicht zwei Objekte
     * @param first
     * Das erste zu vergleichende Objekt
     * @param second
     * Das zweite zu vergleichende Objekt
     * @return boolean
     * Gibt einen Boolean zur�ck der angibt ob die beiden Objekte identisch sind
     */
    public boolean equals(Object first, Object second) {
        Asset firstAsset = (Asset) first;
        Asset secondAsset = (Asset) second;
        if (firstAsset.name.equals(secondAsset.name)) return true;
        else {
        return false;
        }
    }
    /**
     * Share[] longerArray(Share[] sharearray, int howmuchlonger).
     * Verl�ngert ein Array
     * @param sharearray
     * Das zu verl�ngernde Array
     * @param howmuchlonger
     * Um wie viel soll verl�ngert werden
     * @return Share[]
     * gibt das verl�ngerte Array zur�ck
     */
    public Share[] longerArray(Share[] sharearray, int howmuchlonger) {
        Share[] longer = new Share[sharearray.length + howmuchlonger];
        for (int j = 0; j < sharearray.length; j++) {
            longer[j] = sharearray[j];
        }
        return longer;
    }
}