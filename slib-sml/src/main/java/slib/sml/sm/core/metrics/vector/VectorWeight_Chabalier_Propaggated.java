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


package slib.sml.sm.core.metrics.vector;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.openrdf.model.vocabulary.RDF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import slib.sglib.model.graph.G;
import slib.sglib.model.graph.elements.V;
import slib.sglib.model.graph.elements.type.VType;
import slib.utils.impl.ResultStack;

import com.tinkerpop.blueprints.Direction;



public class VectorWeight_Chabalier_Propaggated {


	public static ResultStack<V,Double> compute(G g){

		Logger logger = LoggerFactory.getLogger(VectorWeight_Chabalier_Propaggated.class);

		logger.info("Computing IDF chabalier 2007");

		long nb_annotated_entities = 0;

		Set<V> instances = g.getV(VType.INSTANCE);


		Map<V,Long> nbOcc = new HashMap<V, Long>();

		for (V v :g.getVClass())
			nbOcc.put(v, new Long(0));


		for(V o : instances){
			for(V v :  g.getV(o,RDF.TYPE,Direction.OUT)){
				nb_annotated_entities++;
				nbOcc.put(v,nbOcc.get(v)+1);
			}
		}

		ResultStack<V,Double> stack = new ResultStack<V,Double>();


		for (V v :g.getVClass()){

			// Add 1 to original formula to avoid 0 division
			double idf = Math.log(nb_annotated_entities/(nbOcc.get(v)+1));

			stack.add(v, idf);

			//logger.debug(v+"\t\t"+nbOcc.get(v)+"\t"+idf);

		}
		return stack;
	}
}