/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.admin;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Admin;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Hrckok
 */
public class SOLoginAdmin extends AbstractSO {

    private Admin ulogovaniAdmin;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Admin)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Admin!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        Admin admin = (Admin) ado;
        ArrayList<Admin> admini = (ArrayList<Admin>) (ArrayList<?>) DBBroker.getInstance().select(admin);
        for (Admin a : admini) {
            if (a.getUsername().equals(admin.getUsername()) && a.getPassword().equals(admin.getPassword())) {
                ulogovaniAdmin = a;
                return;
            }
        }
    }

    public Admin getUlogovaniAdmin() {
        return ulogovaniAdmin;
    }
    

}
