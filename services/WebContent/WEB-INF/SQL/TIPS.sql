USE [master]
GO

IF EXISTS(SELECT name FROM sys.databases WHERE name = 'TIPS')
	DROP DATABASE [TIPS]
GO

CREATE DATABASE [TIPS] 
GO

IF EXISTS(SELECT * FROM sys.syslogins WHERE name = N'tips')
	DROP LOGIN [tips]
GO

CREATE LOGIN [tips] WITH PASSWORD = 'tips', CHECK_POLICY = OFF
GO

USE [TIPS]
GO

IF  EXISTS (SELECT * FROM sys.database_principals WHERE name = N'tips') 
	DROP USER [tips]
GO

CREATE USER [tips] FOR LOGIN [tips] WITH DEFAULT_SCHEMA=[dbo]
GO

EXEC sp_addrolemember 'db_owner', N'tips'
GO

/****** Object:  Table [dbo].[Class]    Script Date: 8/15/2012 2:01:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Class](
	[Id] [varchar](250) NOT NULL,
	[Name] [varchar](300) NOT NULL,
	[RDL_Class_Id] [varchar](250) NOT NULL,
 CONSTRAINT [PK_Class] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Commodity]    Script Date: 8/15/2012 2:01:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Commodity](
	[Id] [varchar](250) NOT NULL,
	[Name] [varchar](250) NOT NULL,
	[RDL_Class_ID] [varchar](900) NULL,
 CONSTRAINT [PK_Commodity] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Commodity_Tip]    Script Date: 8/15/2012 2:01:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Commodity_Tip](
	[Tip_Id] [varchar](250) NOT NULL,
	[Commodity_Id] [varchar](250) NOT NULL,
 CONSTRAINT [PK_Commodity_TIP] PRIMARY KEY CLUSTERED 
(
	[Tip_Id] ASC,
	[Commodity_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Ordinal]    Script Date: 8/15/2012 2:01:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Ordinal](
	[Pattern_Id] [varchar](250) NOT NULL,
	[Ordinal] [int] NOT NULL,
	[Template_id] [varchar](250) NOT NULL,
 CONSTRAINT [PK_Ordinal] PRIMARY KEY CLUSTERED 
(
	[Pattern_Id] ASC,
	[Ordinal] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Pattern]    Script Date: 8/15/2012 2:01:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Pattern](
	[Id] [varchar](250) NOT NULL,
	[Name] [varchar](500) NOT NULL,
	[Typical_value] [varchar](250) NULL,
	[Tip_Id] [varchar](250) NOT NULL,
 CONSTRAINT [PK_Pattern] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Role]    Script Date: 8/15/2012 2:01:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Role](
	[Id] [varchar](250) NOT NULL,
	[Template_Id] [varchar](250) NOT NULL,
	[Type] [varchar](250) NULL,
	[Class_Id] [varchar](250) NULL,
	[Individuals] [varchar](250) NULL,
	[RDL_Role_ID] [varchar](250) NOT NULL,
	[Name] [varchar](300) NULL,
	[Role_Name] [varchar](255) NULL,
	[individual] [varchar](255) NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED 
(
	[Id] ASC,
	[Template_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Template]    Script Date: 8/15/2012 2:01:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Template](
	[Id] [varchar](250) NOT NULL,
	[Name] [varchar](300) NOT NULL,
	[Type] [varchar](250) NULL,
	[RDL_Template_Id] [varchar](250) NOT NULL,
	[rdlTemplateId] [varchar](255) NULL,
 CONSTRAINT [PK_Template] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Tip]    Script Date: 8/15/2012 2:01:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Tip](
	[Id] [varchar](250) NOT NULL,
	[Name] [varchar](350) NOT NULL,
	[Description] [varchar](800) NULL,
	[Level] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Tip] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Class] ([Id], [Name], [RDL_Class_Id]) VALUES (N'CLASS0001', N'Pipeline', N'R08hlkjn079234')
INSERT [dbo].[Class] ([Id], [Name], [RDL_Class_Id]) VALUES (N'CLASS0002', N'Lines', N'R04237u90022')
INSERT [dbo].[Commodity] ([Id], [Name], [RDL_Class_ID]) VALUES (N'100', N'Document', N'DOCUMENT')
INSERT [dbo].[Commodity] ([Id], [Name], [RDL_Class_ID]) VALUES (N'101', N'Equipment', N'EQUIPMENT')
INSERT [dbo].[Commodity] ([Id], [Name], [RDL_Class_ID]) VALUES (N'102', N'Instrument', N'INSTRUMENT')
INSERT [dbo].[Commodity] ([Id], [Name], [RDL_Class_ID]) VALUES (N'103', N'Line', N'PIPING NETWORK')
INSERT [dbo].[Commodity_Tip] ([Tip_Id], [Commodity_Id]) VALUES (N'T0001', N'101 ')
INSERT [dbo].[Commodity_Tip] ([Tip_Id], [Commodity_Id]) VALUES (N'T0001', N'102 ')
INSERT [dbo].[Commodity_Tip] ([Tip_Id], [Commodity_Id]) VALUES (N'T0002', N'101')
INSERT [dbo].[Commodity_Tip] ([Tip_Id], [Commodity_Id]) VALUES (N'T0002', N'102 ')
INSERT [dbo].[Ordinal] ([Pattern_Id], [Ordinal], [Template_id]) VALUES (N'P0001', 0, N'TEM0001')
INSERT [dbo].[Ordinal] ([Pattern_Id], [Ordinal], [Template_id]) VALUES (N'P0001', 1, N'TEM0002')
INSERT [dbo].[Pattern] ([Id], [Name], [Typical_value], [Tip_Id]) VALUES (N'P0001', N'pattern1', N'67', N'T0001 ')
INSERT [dbo].[Pattern] ([Id], [Name], [Typical_value], [Tip_Id]) VALUES (N'P0002 ', N'pattern2ot tip1 ', N'6yh', N'T0001')
INSERT [dbo].[Role] ([Id], [Template_Id], [Type], [Class_Id], [Individuals], [RDL_Role_ID], [Name], [Role_Name], [individual]) VALUES (N'ROL0001', N'TEM0001', N'Reference', N'CLASS0001', NULL, N'R08823nmo02', N'ClassifiedIdentification', NULL, NULL)
INSERT [dbo].[Role] ([Id], [Template_Id], [Type], [Class_Id], [Individuals], [RDL_Role_ID], [Name], [Role_Name], [individual]) VALUES (N'ROL0002', N'TEM0002', N'Data property', NULL, N'34', N'R0980721313', N'IdentifiedClassification', NULL, NULL)
INSERT [dbo].[Role] ([Id], [Template_Id], [Type], [Class_Id], [Individuals], [RDL_Role_ID], [Name], [Role_Name], [individual]) VALUES (N'ROL0003', N'TEM0001', N'Property', NULL, N'56', N'R0809812313r', N'identified', NULL, NULL)
INSERT [dbo].[Template] ([Id], [Name], [Type], [RDL_Template_Id], [rdlTemplateId]) VALUES (N'TEM0001', N'TemplateOne', N'Qualification', N'R000034', NULL)
INSERT [dbo].[Template] ([Id], [Name], [Type], [RDL_Template_Id], [rdlTemplateId]) VALUES (N'TEM0002', N'TemplateTwo', N'Definition', N'R0343224 ', NULL)
INSERT [dbo].[Tip] ([Id], [Name], [Description], [Level]) VALUES (N'T0001', N'diameter', N'abcd', N'')
INSERT [dbo].[Tip] ([Id], [Name], [Description], [Level]) VALUES (N'T0002', N'Daimeter 22', N'jknlkm', N'')
ALTER TABLE [dbo].[Commodity_Tip]  WITH CHECK ADD  CONSTRAINT [FK_Commodity_Tip_Commodity_Tip] FOREIGN KEY([Commodity_Id])
REFERENCES [dbo].[Commodity] ([Id])
GO
ALTER TABLE [dbo].[Commodity_Tip] CHECK CONSTRAINT [FK_Commodity_Tip_Commodity_Tip]
GO
ALTER TABLE [dbo].[Commodity_Tip]  WITH CHECK ADD  CONSTRAINT [FK_Commodity_Tip_Tip] FOREIGN KEY([Tip_Id])
REFERENCES [dbo].[Tip] ([Id])
GO
ALTER TABLE [dbo].[Commodity_Tip] CHECK CONSTRAINT [FK_Commodity_Tip_Tip]
GO
ALTER TABLE [dbo].[Ordinal]  WITH CHECK ADD  CONSTRAINT [FK_Ordinal_Ordinal] FOREIGN KEY([Template_id])
REFERENCES [dbo].[Template] ([Id])
GO
ALTER TABLE [dbo].[Ordinal] CHECK CONSTRAINT [FK_Ordinal_Ordinal]
GO
ALTER TABLE [dbo].[Ordinal]  WITH CHECK ADD  CONSTRAINT [FK_Ordinal_Pattern] FOREIGN KEY([Pattern_Id])
REFERENCES [dbo].[Pattern] ([Id])
GO
ALTER TABLE [dbo].[Ordinal] CHECK CONSTRAINT [FK_Ordinal_Pattern]
GO
ALTER TABLE [dbo].[Pattern]  WITH CHECK ADD  CONSTRAINT [FK_Pattern_Tip] FOREIGN KEY([Tip_Id])
REFERENCES [dbo].[Tip] ([Id])
GO
ALTER TABLE [dbo].[Pattern] CHECK CONSTRAINT [FK_Pattern_Tip]
GO
ALTER TABLE [dbo].[Role]  WITH CHECK ADD  CONSTRAINT [FK_Role_Class] FOREIGN KEY([Class_Id])
REFERENCES [dbo].[Class] ([Id])
GO
ALTER TABLE [dbo].[Role] CHECK CONSTRAINT [FK_Role_Class]
GO
ALTER TABLE [dbo].[Role]  WITH CHECK ADD  CONSTRAINT [FK_Role_Template] FOREIGN KEY([Template_Id])
REFERENCES [dbo].[Template] ([Id])
GO
ALTER TABLE [dbo].[Role] CHECK CONSTRAINT [FK_Role_Template]
GO
USE [master]
GO
ALTER DATABASE [TIPS] SET  READ_WRITE 
GO
