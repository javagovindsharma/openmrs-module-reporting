package org.openmrs.module.reporting.cohort.definition.evaluator;

import java.util.Date;

import org.openmrs.Cohort;
import org.openmrs.annotation.Handler;
import org.openmrs.api.context.Context;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.InProgramCohortDefinition;
import org.openmrs.module.reporting.cohort.query.service.CohortQueryService;
import org.openmrs.module.reporting.evaluation.EvaluationContext;

@Handler(supports={InProgramCohortDefinition.class})
public class InProgramCohortDefinitionEvaluator implements CohortDefinitionEvaluator {

	/**
	 * Default constructor
	 */
	public InProgramCohortDefinitionEvaluator() {
	}
	
	/**
	 * @see org.openmrs.module.reporting.cohort.definition.evaluator.CohortDefinitionEvaluator#evaluate(org.openmrs.module.reporting.cohort.definition.CohortDefinition, org.openmrs.module.reporting.evaluation.EvaluationContext)
	 */
	public Cohort evaluate(CohortDefinition cohortDefinition, EvaluationContext context) {
		InProgramCohortDefinition definition = (InProgramCohortDefinition) cohortDefinition;
		Date onOrAfter = definition.getOnOrAfter();
		Date onOrBefore = definition.getOnOrBefore();
		if (definition.getOnDate() != null) {
			onOrAfter = definition.getOnDate();
			onOrBefore = definition.getOnDate();
		}
		return Context.getService(CohortQueryService.class).getPatientsInProgram(definition.getPrograms(), onOrAfter, onOrBefore);
	}
	
}