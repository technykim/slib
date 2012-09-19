/*

Copyright or © or Copr. Ecole des Mines d'Alès (2012) 

This software is a computer program whose purpose is to 
process semantic graphs.

This software is governed by the CeCILL  license under French law and
abiding by the rules of distribution of free software.  You can  use, 
modify and/ or redistribute the software under the terms of the CeCILL
license as circulated by CEA, CNRS and INRIA at the following URL
"http://www.cecill.info". 

As a counterpart to the access to the source code and  rights to copy,
modify and redistribute granted by the license, users are provided only
with a limited warranty  and the software's author,  the holder of the
economic rights,  and the successive licensors  have only  limited
liability. 

In this respect, the user's attention is drawn to the risks associated
with loading,  using,  modifying and/or developing or reproducing the
software by the user in light of its specific status of free software,
that may mean  that it is complicated to manipulate,  and  that  also
therefore means  that it is reserved for developers  and  experienced
professionals having in-depth computer knowledge. Users are therefore
encouraged to load and test the software's suitability as regards their
requirements in conditions enabling the security of their systems and/or 
data to be ensured and,  more generally, to use and operate it in the 
same conditions as regards security. 

The fact that you are presently reading this means that you have had
knowledge of the CeCILL license and that you accept its terms.

 */
 
 
package slib.sml.sm.core.measures.graph.groupwise.dag;

import java.util.Set;

import slib.sglib.model.graph.elements.V;
import slib.sml.sm.core.utils.SM_manager;
import slib.sml.sm.core.utils.SMconf;
import slib.utils.impl.SetUtils;


/**
 * ﻿Mistry M, Pavlidis P: 
 * Gene Ontology term overlap as a measure of gene functional similarity. 
 * BMC bioinformatics 2008, 9:327.
 * 
 * @author seb
 *
 */
public class Sim_groupwise_DAG_NTO extends Sim_groupwise_DAG_abstract{

	public double sim(Set<V> setA, Set<V> setB, SM_manager rc, SMconf conf) {
		
		Set<V> ancA = rc.getAncestors(setA);
		Set<V> ancB = rc.getAncestors(setB);
		
		Set<V> intersection = SetUtils.intersection(ancA, ancB);
		
		return (double) intersection.size()/ Math.min(ancA.size(), ancB.size());
	}
}
