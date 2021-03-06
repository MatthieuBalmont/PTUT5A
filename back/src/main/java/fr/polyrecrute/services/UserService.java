package fr.polyrecrute.services;

import fr.polyrecrute.models.Company__;
import fr.polyrecrute.models.Entity__;
import fr.polyrecrute.models.Offer__;
import fr.polyrecrute.models.User__;
import fr.polyrecrute.responceType.User;
import fr.polyrecrute.responceType.UserSignup;

import java.util.List;
import java.util.Set;

public interface UserService {
    void registerEntity(UserSignup userSignup);
    List<User> getTransactionalObjectList(List<User__> users);
    void deleteWantedOffer(User__ user, Offer__ offer);
    void addWantedOffer(User__ user, Offer__ offer);
    List<User__> findUserWhoWantedOffer(Company__ company);
    public long countUserWithoutOffer();
}
