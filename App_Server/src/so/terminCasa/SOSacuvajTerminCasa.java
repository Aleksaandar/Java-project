/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.terminCasa;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Dokument;
import domain.TerminCasa;
import java.sql.*;
import java.util.List;
import so.AbstractSO;
import so.dokument.SOSacuvajDokument;

/**
 *
 * @author Hrckok
 */
public class SOSacuvajTerminCasa extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof TerminCasa)) {
            throw new Exception("Prosledjeni objekat nije instanca klase TerminCasa!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        TerminCasa terminCasa = (TerminCasa) ado;

        // Čuvanje termina i dokumenata u bazi podataka
        PreparedStatement ps = DBBroker.getInstance().insert(terminCasa);
        ResultSet rs = ps.getGeneratedKeys();
        int generatedId = -1;
        if (rs.next()) {
            generatedId = rs.getInt(1);
        }
        terminCasa.setTerminCasaID(generatedId);

        List<Dokument> dokumenti = terminCasa.getDokumenti();
        SOSacuvajDokument so1=new SOSacuvajDokument();
        for (Dokument dokument : dokumenti) {
             dokument.setTerminCasa(terminCasa);
             so1.templateExecute(dokument);
        }

        rs.close();
        ps.close();

    }
}
