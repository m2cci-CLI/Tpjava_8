package java8.ex02;

import java8.data.Account;
import java8.data.Data;
import java8.data.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Exercice 02 - Map
 */
public class Lambda_02_Test {

    // tag::PersonToAccountMapper[]
    interface PersonToAccountMapper {
        Account map(Person p);
    }
    
    interface PersonToPrenomMapper {
    	String map(Person p);
    }
    // end::PersonToAccountMapper[]

    // tag::map[]
    private List<Account> mapToAccount(List<Person> personList, PersonToAccountMapper mapper) {
        // TODO implémenter la méthode
    	List<Account> accountArray = new ArrayList<Account>();
    	
    	for(Person person: personList) {
		
    		accountArray.add(mapper.map(person));	
		}
        return accountArray ;
        }
    

    private List<String> mapToprenom(List<Person> persons, PersonToPrenomMapper mapper) {
       // TODO implémenter la méthode
   	List<String> listedeprenom = new ArrayList<String>();
   	
   	for(Person person: persons) {
		
   		listedeprenom.add(mapper.map(person));
				
		}

       return listedeprenom ;
     
   }
   
    
    // end::map[]


    // tag::test_map_person_to_account[]
    @Test
    public void test_map_person_to_account() throws Exception {

        List<Person> personList = Data.buildPersonList(100);

        // TODO transformer la liste de personnes en liste de comptes
        // TODO tous les objets comptes ont un solde à 100 par défaut
        
        List<Account> result = mapToAccount(personList,t->{
        	Account account = new Account();
        	account.setBalance(100); 
        	account.setOwner((Person) t);
        	return account;});

        assertThat(result, hasSize(personList.size()));
        assertThat(result, everyItem(hasProperty("balance", is(100))));
        assertThat(result, everyItem(hasProperty("owner", notNullValue())));
    }
    // end::test_map_person_to_account[]

    // tag::test_map_person_to_firstname[]
    @Test
    public void test_map_person_to_firstname() throws Exception {

        List<Person> personList = Data.buildPersonList(100);

        // TODO transformer la liste de personnes en liste de prénoms
  
        
        List<String> result =mapToprenom(personList,p->p.getFirstname());
        

        assertThat(result, hasSize(personList.size()));
        assertThat(result, everyItem(instanceOf(String.class)));
        assertThat(result, everyItem(startsWith("first")));
    }
    // end::test_map_person_to_firstname[]
}
