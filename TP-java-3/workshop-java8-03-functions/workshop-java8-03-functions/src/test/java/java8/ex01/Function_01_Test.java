package java8.ex01;

import java8.data.Account;
import java8.data.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


/**
 * Exercice 01 - java.util.function.Function
 */
public class Function_01_Test {

    /******** PART 1 - Integer -> Person *******/
	
    // tag::intToPerson[]
	
	
    // TODO Compléter la définition de cette fonction
    // TODO Cette fonction permet de transformer un entier en objet Person
    // TODO le prenom sera de la forme "first_<ENTIER>"
    // TODO le nom sera de la forme "last_<ENTIER>"
    // TODO l'age sera de la forme "<ENTIER>"
    // TODO le mot de passe sera de la forme "pass_<ENTIER>"
    private Function<Integer, Person> intToPerson =T->{ 
    	Person person=new Person();
    	
    	person.setFirstname("first_"+ T);
    	person.setLastname("last_"+ T); 
    	person.setAge(T);
    	person.setPassword("pass_"+ T); 
    	return person ;
    };
    // end::intToPerson[]

    @Test
    public void test_intToPerson() throws Exception {
    	Person person=new Person();

        // TODO invoquer la fonction intToPerson avec en paramètre l'entier 10.
        Person result =intToPerson.apply(10);

        assertThat(result, hasProperty("firstname", is("first_10")));
        assertThat(result, hasProperty("lastname", is("last_10")));
        assertThat(result, hasProperty("age", is(10)));
        assertThat(result, hasProperty("password", is("pass_10")));
    }

    /******** PART 2 - Person -> Account *******/

    // tag::personToAccount[]
    // TODO Compléter la définition de cette fonction
    // TODO la propriété owner est valorisé avec la personne en paramètre
    // TODO la propriété balance est valorisé à 1000
    private Function<Person, Account> personToAccount = t->{
    	Account account  = new Account();
    	((Account) account).setOwner((Person) t);
    	((Account) account).setBalance(1000);
    	return (Account) account;};

    
    // end::personToAccount[]

    @Test
    public void test_personToAccount() throws Exception {

        Person person = new Person("Jules", "France", 10, "pass");

        // TODO invoquer la fonction personToAccount
        Account result = personToAccount.apply(person);

        assertThat(result, hasProperty("owner", is(person)));
        assertThat(result, hasProperty("balance", is(1000)));
    }


    /******** PART 3 - Integer -> Account avec compose *******/

    // tag::intToAccountWithCompose[]
    // TODO Compléter la définition de cette fonction
    // TODO Utiliser la méthode compose pour réutiliser les fonctions intToPerson et personToAccount
    private Function<Integer, Account> intToAccountWithCompose = personToAccount.compose(intToPerson);
	
    // end::intToAccountWithCompose[]


    @Test
    public void test_intToAccount_with_Compose() throws Exception {

        // TODO invoquer la fonction intToAccountWithCompose avec l'entier 10
        Account result = intToAccountWithCompose.apply(10);

        assertThat(result.getOwner(), hasProperty("firstname", is("first_10")));
        assertThat(result, hasProperty("balance", is(1000)));
    }

    /******** PART 4 - Integer -> Account avec andThen *******/

    // tag::intToAccountWithAndThen[]
    // TODO Compléter la définition de cette fonction
    // TODO Utiliser la méthode andThen pour réutiliser les fonctions intToPerson et personToAccount
    private Function<Integer, Account> intToAccountWithAndThen = intToPerson.andThen(personToAccount);
    // end::intToAccountWithAndThen[]

    @Test
    public void test_intToAccount_with_AndThen() throws Exception {

        // TODO invoquer la fonction intToAccountWithAndThen avec l'entier 11
        Account result = intToAccountWithAndThen.apply(11);

        assertThat(result.getOwner(), hasProperty("firstname", is("first_11")));
        assertThat(result, hasProperty("balance", is(1000)));
    }
}
