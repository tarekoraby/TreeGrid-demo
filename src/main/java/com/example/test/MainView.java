package com.example.test;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.router.Route;
import java.util.ArrayList;
import java.util.List;

@Route
public class MainView extends VerticalLayout {

	public MainView() {
		DepartmentData departmentData = new DepartmentData(100);
		departmentData.rootItems.forEach(r -> {
			departmentData.addChildren(r);
			r.children.forEach(departmentData::addChildren);
		});

		TreeGrid<Department> grid = new TreeGrid<>();
		setSizeFull();
		grid.setSizeFull();
		
		grid.setItems(departmentData.getRootDepartments(), departmentData::getChildDepartments);
		grid.addHierarchyColumn(Department::getName).setHeader("Department Name");
		grid.addColumn(Department::getcell1).setHeader("column 1");
		grid.addColumn(Department::getcell2).setHeader("column 2");
		grid.addColumn(Department::getcell3).setHeader("column 3");
		grid.addColumn(Department::getcell4).setHeader("column 4");
		grid.addColumn(Department::getcell5).setHeader("column 5");
		grid.addColumn(Department::getcell6).setHeader("column 6");
		grid.addColumn(Department::getcell7).setHeader("column 7");
		grid.addColumn(Department::getcell8).setHeader("column 8");

		Button expand = new Button("Expand All");
		expand.addClickListener(e -> {
			grid.expandRecursively(departmentData.getRootDepartments(), 2);
		});

		add(grid, expand);
	}

	static class DepartmentData {
		private List<Department> rootItems;

		private DepartmentData(int rootDepartments) {
			rootItems = new ArrayList<>();

			for (int i = 0; i < rootDepartments; i++) {
				rootItems.add(new Department(i));
			}
		}

		public List<Department> getRootDepartments() {
			return rootItems;
		}

		public List<Department> getChildDepartments(Department parent) {
			return parent.children;
		}

		public void addChildren(Department parent) {
			for (int i = 0; i < 3; i++) {
				Department child = new Department("Sub-", i);
				parent.children.add(child);
				for (int x = 0; x < 3; x++) {
					Department gc = new Department("SubSub-", x);
					child.children.add(gc);
				}
			}
		}
	}

	static class Department {
		private final String cell1;
		private final String cell2;
		private final String cell3;
		private final String cell4;
		private final String cell5;
		private final String cell6;
		private final String cell7;
		private final String cell8;
		private final String name;
		private final List<Department> children;

		public Department(String prefix, int depNumber) {
			this.name = prefix + depNumber;
			this.cell1 = "content";
			this.cell2 = "content";
			this.cell3 = "content";
			this.cell4 = "content";
			this.cell5 = "content";
			this.cell6 = "content";
			this.cell7 = "content";
			this.cell8 = "content";
			this.children = new ArrayList<>();
		}

		public Department(int depNumber) {
			this("", depNumber);
		}

		public String getName() {
			return name;
		}

		public String getcell1() {
			return cell1;
		}

		public String getcell2() {
			return cell2;
		}

		public String getcell3() {
			return cell3;
		}

		public String getcell4() {
			return cell4;
		}

		public String getcell5() {
			return cell5;
		}

		public String getcell6() {
			return cell6;
		}

		public String getcell7() {
			return cell7;
		}

		public String getcell8() {
			return cell8;
		}

		public List<Department> getChildren() {
			return children;
		}
	}
}