/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.student;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Dokument;
import domain.Student;
import domain.TerminCasa;
import java.util.ArrayList;
import javax.crypto.AEADBadTagException;
import so.AbstractSO;
import so.dokument.SOIzbrisiDokument;
import so.terminCasa.SOIzbrisiTerminCasa;

/**
 *
 * @author Hrckok
 */
public class SOObrisiStudenta extends AbstractSO {
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Student)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Student!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        Student student = (Student) ado;

        Dokument dokumentZaBrisanje=new Dokument();
        TerminCasa terminCasaZaBrisanje=new TerminCasa();
        terminCasaZaBrisanje.setStudent(student);
        dokumentZaBrisanje.setTerminCasa(terminCasaZaBrisanje);
        SOIzbrisiDokument so=new SOIzbrisiDokument();
        so.templateExecute(dokumentZaBrisanje);
        
        SOIzbrisiTerminCasa sot=new SOIzbrisiTerminCasa();
        sot.templateExecute(terminCasaZaBrisanje);

        // Brisanje iz glavne tabele Student
        DBBroker.getInstance().delete((Student)student);
    }
    
}
