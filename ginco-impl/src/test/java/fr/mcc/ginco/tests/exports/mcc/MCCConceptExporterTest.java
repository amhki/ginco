/**
 * Copyright or © or Copr. Ministère Français chargé de la Culture
 * et de la Communication (2013)
 * <p/>
 * contact.gincoculture_at_gouv.fr
 * <p/>
 * This software is a computer program whose purpose is to provide a thesaurus
 * management solution.
 * <p/>
 * This software is governed by the CeCILL license under French law and
 * abiding by the rules of distribution of free software. You can use,
 * modify and/ or redistribute the software under the terms of the CeCILL
 * license as circulated by CEA, CNRS and INRIA at the following URL
 * "http://www.cecill.info".
 * <p/>
 * As a counterpart to the access to the source code and rights to copy,
 * modify and redistribute granted by the license, users are provided only
 * with a limited warranty and the software's author, the holder of the
 * economic rights, and the successive licensors have only limited liability.
 * <p/>
 * In this respect, the user's attention is drawn to the risks associated
 * with loading, using, modifying and/or developing or reproducing the
 * software by the user in light of its specific status of free software,
 * that may mean that it is complicated to manipulate, and that also
 * therefore means that it is reserved for developers and experienced
 * professionals having in-depth computer knowledge. Users are therefore
 * encouraged to load and test the software's suitability as regards their
 * requirements in conditions enabling the security of their systemsand/or
 * data to be ensured and, more generally, to use and operate it in the
 * same conditions as regards security.
 * <p/>
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL license and that you accept its terms.
 */
package fr.mcc.ginco.tests.exports.mcc;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fr.mcc.ginco.beans.ThesaurusConcept;
import fr.mcc.ginco.exports.mcc.MCCConceptExporter;
import fr.mcc.ginco.exports.result.bean.JaxbList;
import fr.mcc.ginco.services.IAssociativeRelationshipService;
import fr.mcc.ginco.services.INoteService;

/**
 * This component is in charge of exporting collections to SKOS
 *
 */
public class MCCConceptExporterTest {
	
	@Mock(name="noteService")
	private INoteService noteService;
	
	@Mock(name="associativeRelationshipService")
	private IAssociativeRelationshipService associativeRelationshipService;
	
	@InjectMocks
	MCCConceptExporter mccConceptExporter;
	
	@Before
	public void init() {		
			MockitoAnnotations.initMocks(this);	
	}
	
	@Test
	public void testGetExportHierarchicalConcepts() {
		
		ThesaurusConcept c1 = new ThesaurusConcept();
		c1.setIdentifier("http://c1");
		
		ThesaurusConcept c2 = new ThesaurusConcept();
		c2.setIdentifier("http://c2");
		
		ThesaurusConcept c3 = new ThesaurusConcept();
		c3.setIdentifier("http://c3");
		
		Set<ThesaurusConcept> fakeParents = new HashSet<ThesaurusConcept>();
		fakeParents.add(c2);
		fakeParents.add(c3);
		c1.setParentConcepts(fakeParents);
		
		JaxbList<String> result = mccConceptExporter.getExportHierarchicalConcepts(c1);
		Assert.assertEquals(result.getList().size(), c1.getParentConcepts().size());
	}
}