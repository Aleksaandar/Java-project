/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.admin;

import controller.Controller;
import domain.AbstractDomainObject;
import domain.Admin;
import so.AbstractSO;

/**
 *
 * @author Hrckok
 */
public class SOLogoutAdmina extends AbstractSO {
     private Admin odjavljeniAdmin;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Admin)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Admin!");
        }

        Admin admin = (Admin) ado;

        if (!Controller.getInstance().isAdminLoggedIn(admin)) {
            throw new Exception("Menadzer nije prijavljen!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        Admin admin = (Admin) ado;
        Controller.getInstance().getServer().removeLoggedInAdmin(admin);
        odjavljeniAdmin = admin;
    }

    public Admin getOdjavljeniMenadzer() {
        return odjavljeniAdmin;
    }
    
}
