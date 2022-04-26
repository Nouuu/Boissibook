package org.esgi.boissibook.hexagonal;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

class ArchitectureTest {
    JavaClasses projectClasses;

    @BeforeEach
    void setup() {
        projectClasses = new ClassFileImporter().importPackages("org.esgi.boissibook");
    }

    @Test
    void should_domain_never_be_linked_with_frameworks() {
        var ruleNoFramework = noClasses().that().resideInAPackage("..domain..")
                .should().dependOnClassesThat().resideInAPackage("..springframework..")
                .orShould().dependOnClassesThat().resideInAPackage("javax..");

        ruleNoFramework.check(projectClasses);
    }

    @Test
    void should_kernel_never_be_linked_with_frameworks() {
        var ruleNoFramework = noClasses().that().resideInAPackage("..kernel..")
                .should().dependOnClassesThat().resideInAPackage("..springframework..")
                .orShould().dependOnClassesThat().resideInAPackage("javax..");

        ruleNoFramework.check(projectClasses);
    }

    @Test
    void should_respect_hexagonal_architecture() {
        var ruleLayerAccess = layeredArchitecture()
                .layer("domain").definedBy("..domain..")
                .layer("infra").definedBy("..infra..")

                .whereLayer("domain").mayOnlyBeAccessedByLayers("infra");

        ruleLayerAccess.check(projectClasses);
    }

}
