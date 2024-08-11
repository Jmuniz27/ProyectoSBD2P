import mysql.connector
from mysql.connector import Error
import tkinter as tk
from tkinter import ttk, messagebox

# Función para conectar a la base de datos
def connect():
    try:
        connection = mysql.connector.connect(
            host='proyectosbdgrupo8.mysql.database.azure.com',
            user='main',
            password='proyectoSBD=Grupo8_',
            database='wuanplus'
        )
        return connection
    except Error as e:
        messagebox.showwarning("Error", "No se pudo conectar a la base de datos")
        print(f"Error al conectar a MySQL: {e}")
        return None

# Ventana para la gestión de empleados
class EmpleadosWindow(tk.Toplevel):
    def __init__(self, parent):
        super().__init__(parent)
        self.title("Gestión de Empleados")
        self.geometry("600x400")
        self.parent = parent

        self.create_widgets()

    def create_widgets(self):
        #labels
        # tk.Label(self, text="Texto del campo").grid(row=número_fila, column=número_columna, padx=espacio_entre_label_y_campo, pady=espacio_entre_label_y_campo
        tk.Label(self, text="ID Empleado:").grid(row=0, column=0, padx=10, pady=5)
        # cuadro de texto para ingresar datos
        # tk.Entry(self).grid(row=número_fila, column=número_columna)
        self.entry_id = tk.Entry(self)
        self.entry_id.grid(row=0, column=1)

        tk.Label(self, text="Nombre:").grid(row=1, column=0, padx=10, pady=5)
        self.entry_nombre = tk.Entry(self)
        self.entry_nombre.grid(row=1, column=1)

        tk.Label(self, text="Puesto:").grid(row=2, column=0, padx=10, pady=5)
        self.entry_puesto = tk.Entry(self)
        self.entry_puesto.grid(row=2, column=1)

        # Agrega más campos según sea necesario

        # Botones
        # tk.button(self, text="Texto del botón", command=función_a_ejecutar).grid(row=número_fila, column=número_columna, pady=espacio_entre_botones)
        tk.Button(self, text="Agregar Empleado", command=self.add_empleado).grid(row=3, column=0, pady=10)
        tk.Button(self, text="Consultar Empleado", command=self.get_empleado).grid(row=3, column=1, pady=10)
        tk.Button(self, text="Regresar", command=self.go_back).grid(row=3, column=2, pady=10)

        self.btn_edit = tk.Button(self, text="Editar", state=tk.DISABLED, command=self.edit_empleado)
        self.btn_edit.grid(row=4, column=0, pady=10)

        self.btn_delete = tk.Button(self, text="Eliminar", state=tk.DISABLED, command=self.delete_empleado)
        self.btn_delete.grid(row=4, column=1, pady=10)

        self.tree = ttk.Treeview(self, columns=("ID", "Nombre", "Puesto"), show="headings")
        self.tree.heading("ID", text="ID Empleado")
        self.tree.heading("Nombre", text="Nombre")
        self.tree.heading("Puesto", text="Puesto")
        self.tree.grid(row=5, column=0, columnspan=3, pady=20)

        self.tree.bind('<<TreeviewSelect>>', self.on_tree_select)
    # Función para seleccionar un elemento de la tabla
    def on_tree_select(self, event):
        selected_item = self.tree.selection()
        if selected_item:
            self.selected_item = self.tree.item(selected_item)
            self.btn_edit.config(state=tk.NORMAL)
            self.btn_delete.config(state=tk.NORMAL)
            self.entry_id.delete(0, tk.END)
            self.entry_nombre.delete(0, tk.END)
            self.entry_puesto.delete(0, tk.END)
            # Insertar los valores como cadenas de texto
            self.entry_id.insert(0, str(self.selected_item['values'][0]))
            self.entry_nombre.insert(0, str(self.selected_item['values'][1]))
            self.entry_puesto.insert(0, str(self.selected_item['values'][2]))
            # Animación para resaltar
            self.entry_nombre.config(bg='light blue')
            self.entry_id.config(bg='light blue')
            self.entry_puesto.config(bg='light blue')
            self.update()
            self.after(200)
            self.entry_id.config(bg='white')
            self.entry_nombre.config(bg='white')
            self.entry_puesto.config(bg='white')
            self.update()
    def add_empleado(self):
        id_empleado = self.entry_id.get()
        nombre = self.entry_nombre.get()
        puesto = self.entry_puesto.get()

        query = """INSERT INTO empleado (id_empleado, nombre, puesto) VALUES (%s, %s, %s)"""
        values = (id_empleado, nombre, puesto)
        connection = connect()
        if connection:
            cursor = connection.cursor()
            cursor.execute(query, values)
            connection.commit()
            cursor.close()
            connection.close()
            messagebox.showinfo("Éxito", "Empleado agregado exitosamente")
            self.get_empleado()

    def get_empleado(self):
        id_empleado = self.entry_id.get()
        connection = connect()
        if connection:
            cursor = connection.cursor()
            if id_empleado:
                query = "SELECT id_empleado, nombre, puesto FROM empleado WHERE id_empleado = %s"
                cursor.execute(query, (id_empleado,))
            else:
                # si el entry esta vacio
                # va a traer todos los empleados
                query = "SELECT id_empleado, nombre, puesto FROM empleado"
                cursor.execute(query)
            
            results = cursor.fetchall()
            cursor.close()
            connection.close()
            

            if results:
                self.tree.delete(*self.tree.get_children())
                for result in results:
                    # Asegurarse de que todos los valores se traten como cadenas de texto
                    str_result = tuple(str(item) for item in result)
                    print(str_result)
                    self.tree.insert("", "end", values=str_result)
            else:
                messagebox.showwarning("Error", "Empleado no encontrado")


    def edit_empleado(self):
        if self.selected_item:
            id_empleado = self.entry_id.get()
            print(id_empleado)
            nombre = self.entry_nombre.get()
            puesto = self.entry_puesto.get()
            old_id = self.selected_item['values'][0]

            query = "UPDATE empleado SET id_empleado = %s, nombre = %s, puesto = %s WHERE id_empleado = %s"
            values = (id_empleado, nombre, puesto, old_id)
            connection = connect()
            if connection:
                cursor = connection.cursor()
                cursor.execute(query, values)
                connection.commit()
                cursor.close()
                connection.close()
                messagebox.showinfo("Éxito", "Empleado actualizado exitosamente")
                self.get_empleado()

    def delete_empleado(self):
        if self.selected_item:
            id_empleado = self.selected_item['values'][0]

            query = "DELETE FROM empleado WHERE id_empleado = %s"
            connection = connect()
            if connection:
                cursor = connection.cursor()
                cursor.execute(query, (id_empleado,))
                connection.commit()
                cursor.close()
                connection.close()
                messagebox.showinfo("Éxito", "Empleado eliminado exitosamente")
                self.get_empleado()

    def go_back(self):
        self.destroy()
        self.parent.current_window = None

# Ventana para la gestión de departamentos
class DepartamentosWindow(tk.Toplevel):
    def __init__(self, parent):
        super().__init__(parent)
        self.title("Gestión de Departamentos")
        self.geometry("600x400")
        self.parent = parent

        self.create_widgets()

    def create_widgets(self):
        tk.Label(self, text="ID Departamento:").grid(row=0, column=0, padx=10, pady=5)
        self.entry_id = tk.Entry(self)
        self.entry_id.grid(row=0, column=1)

        tk.Label(self, text="Nombre:").grid(row=1, column=0, padx=10, pady=5)
        self.entry_nombre = tk.Entry(self)
        self.entry_nombre.grid(row=1, column=1)

        tk.Button(self, text="Agregar Departamento", command=self.add_departamento).grid(row=2, column=0, pady=10)
        tk.Button(self, text="Consultar Departamento", command=self.get_departamento).grid(row=2, column=1, pady=10)
        tk.Button(self, text="Regresar", command=self.go_back).grid(row=2, column=2, pady=10)

        self.btn_edit = tk.Button(self, text="Editar", state=tk.DISABLED, command=self.edit_departamento)
        self.btn_edit.grid(row=3, column=0, pady=10)

        self.btn_delete = tk.Button(self, text="Eliminar", state=tk.DISABLED, command=self.delete_departamento)
        self.btn_delete.grid(row=3, column=1, pady=10)

        self.tree = ttk.Treeview(self, columns=("ID", "Nombre"), show="headings")
        self.tree.heading("ID", text="ID Departamento")
        self.tree.heading("Nombre", text="Nombre")
        self.tree.grid(row=4, column=0, columnspan=3, pady=20)

        self.tree.bind('<<TreeviewSelect>>', self.on_tree_select)

    def on_tree_select(self, event):
        selected_item = self.tree.selection()
        if selected_item:
            self.selected_item = self.tree.item(selected_item)
            self.btn_edit.config(state=tk.NORMAL)
            self.btn_delete.config(state=tk.NORMAL)
            self.entry_id.delete(0, tk.END)
            self.entry_nombre.delete(0, tk.END)
            self.entry_id.insert(0, self.selected_item['values'][0])
            self.entry_nombre.insert(0, self.selected_item['values'][1])

    def add_departamento(self):
        id_departamento = self.entry_id.get()
        nombre = self.entry_nombre.get()

        query = """INSERT INTO departamento_creativo (id_departamento, nombre) VALUES (%s, %s)"""
        values = (id_departamento, nombre)
        connection = connect()
        if connection:
            cursor = connection.cursor()
            cursor.execute(query, values)
            connection.commit()
            cursor.close()
            connection.close()
            messagebox.showinfo("Éxito", "Departamento agregado exitosamente")
            self.get_departamento()

    def get_departamento(self):
        id_departamento = self.entry_id.get()
        connection = connect()
        if connection:
            cursor = connection.cursor()
            if id_departamento:
                query = "SELECT id_departamento, nombre FROM departamento_creativo WHERE id_departamento = %s"
                cursor.execute(query, (id_departamento,))
            else:
                query = """
                        SELECT id_departamento, nombre FROM departamento_creativo
                        UNION
                        SELECT id_departamento, nombre FROM departamento_produccion
                        UNION
                        SELECT id_departamento, nombre FROM departamento_finanzas;
                        """
                cursor.execute(query)
            
            results = cursor.fetchall()
            cursor.close()
            connection.close()

            if results:
                self.tree.delete(*self.tree.get_children())
                for result in results:
                    self.tree.insert("", "end", values=result)
            else:
                messagebox.showwarning("Error", "Departamento no encontrado")

    def edit_departamento(self):
        if self.selected_item:
            id_departamento = self.entry_id.get()
            nombre = self.entry_nombre.get()
            old_id = self.selected_item['values'][0]

            query = "UPDATE departamento_creativo SET id_departamento = %s, nombre = %s WHERE id_departamento = %s"
            values = (id_departamento, nombre, old_id)
            connection = connect()
            if connection:
                cursor = connection.cursor()
                cursor.execute(query, values)
                connection.commit()
                cursor.close()
                connection.close()
                messagebox.showinfo("Éxito", "Departamento actualizado exitosamente")
                self.get_departamento()

    def delete_departamento(self):
        if self.selected_item:
            id_departamento = self.selected_item['values'][0]

            query = "DELETE FROM departamento_creativo WHERE id_departamento = %s"
            connection = connect()
            if connection:
                cursor = connection.cursor()
                cursor.execute(query, (id_departamento,))
                connection.commit()
                cursor.close()
                connection.close()
                messagebox.showinfo("Éxito", "Departamento eliminado exitosamente")
                self.get_departamento()

    def go_back(self):
        self.destroy()
        self.parent.current_window = None

# Ventana principal de la aplicación
class MainApp(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Sistema de Gestión Empresarial")
        self.geometry("400x300")

        tk.Button(self, text="Gestión de Empleados", command=self.open_empleados_window).pack(pady=10)
        tk.Button(self, text="Gestión de Departamentos", command=self.open_departamentos_window).pack(pady=10)

        self.current_window = None

    def open_empleados_window(self):
        if self.current_window is not None:
            self.current_window.destroy()
        self.current_window = EmpleadosWindow(self)

    def open_departamentos_window(self):
        if self.current_window is not None:
            self.current_window.destroy()
        self.current_window = DepartamentosWindow(self)

if __name__ == "__main__":
    app = MainApp()
    app.mainloop()
