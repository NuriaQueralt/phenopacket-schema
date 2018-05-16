package org.phenopackets.schema.v1.core;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.phenopackets.schema.v1.PhenoPacketTestUtil.toYaml;

/**
 * @author Jules Jacobsen <j.jacobsen@qmul.ac.uk>
 */
public class PedigreeTest {

    /**
     * Test for representing a small family
     * @throws Exception
     */
    @Test
    public void testTrioPedigree() throws Exception {
        Pedigree defaultPedigree = Pedigree.getDefaultInstance();
        assertThat(defaultPedigree.getFamiliesCount(), equalTo(0));

        Pedigree.Person mother = Pedigree.Person.newBuilder()
                .setFamilyId("FAMILY:1")
                .setIndividualId("MOTHER:1")
                .setSex(Pedigree.Person.Sex.FEMALE)
                .setAffectedStatus(Pedigree.Person.AffectedStatus.UNAFFECTED)
                .build();

        Pedigree.Person father = Pedigree.Person.newBuilder()
                .setFamilyId("FAMILY:1")
                .setIndividualId("FATHER:1")
                .setSex(Pedigree.Person.Sex.MALE)
                .setAffectedStatus(Pedigree.Person.AffectedStatus.UNAFFECTED)
                .build();

        Pedigree.Person daughter = Pedigree.Person.newBuilder()
                .setFamilyId("FAMILY:1")
                .setIndividualId("DAUGHTER:1")
                .setMaternalId("MOTHER:1")
                .setPaternalId("FATHER:1")
                .setSex(Pedigree.Person.Sex.FEMALE)
                .setAffectedStatus(Pedigree.Person.AffectedStatus.AFFECTED)
                .build();

        Pedigree.Family trio = Pedigree.Family.newBuilder()
                .setId("FAMILY:1")
                .addPersons(mother)
                .addPersons(father)
                .addPersons(daughter)
                .build();

        Pedigree pedigree = Pedigree.newBuilder()
                .addFamilies(trio)
                .build();

        System.out.println(toYaml(pedigree));

        assertThat(trio.getId(), equalTo("FAMILY:1"));
        assertThat(trio.getPersonsCount(), equalTo(3));
    }

}
